package controller;

import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Docent;
import model.Les;
import model.Opleiding;
import model.Student;
import server.Conversation;
import server.Handler;

public class Controller implements Handler {
	protected Opleiding opleiding;
	protected Conversation conversation;

	public Controller(Opleiding opleiding) {
		this.opleiding = opleiding;
	}

	@Override
	public void handle(Conversation conversation) {
		
		this.conversation = conversation;
		String[] parameters = conversation.getParameter("q").split("/");
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		switch (parameters[0]) {
			case "login":
				JsonObject jsonObjIn = (JsonObject) conversation.getRequestBodyAsJSON();
				try {
					String gebruikersnaam = jsonObjIn.getString("username"); 
					String wachtwoord = jsonObjIn.getString("wachtwoord");
					
					String rol = this.opleiding.login(gebruikersnaam, wachtwoord);
					
					job.add("rol", rol);
				} finally {}
				
				break;
			
			case "student":
				Student student = null;
				try {
					String gebruikersnaam = parameters[1];
					
					student = this.opleiding.getStudentMetGebruikersnaam(gebruikersnaam);
				} catch (Exception e) {
					this.handleError(101);
					
					return;
				}
				
				switch (parameters[2]) {
					case "vakken":
						job.add("vakken", new VakController(opleiding).vakkenStudent(student));
						break;
					
					case "rooster":
						job.add("rooster", new RoosterController(opleiding).roosterStudent(student));
						break;	
					
					case "medestudenten":
						job.add("medestudenten", new StudentController(opleiding).mijnMedestudenten(student));
						break;
					
					case "afmelden":
						try {
							JsonObject json = (JsonObject) conversation.getRequestBodyAsJSON();
							
							LocalDateTime begintijd = LocalDateTime.parse(json.getJsonObject("les").getString("begintijd"));
							String lokaal = json.getJsonObject("les").getString("lokaal");
							boolean status = json.getBoolean("afmelden");
							String reden = json.getString("afmeldenReden");
							
							Les les = this.opleiding.getLes(begintijd, lokaal);
							
							job.add("les", new StudentController(opleiding).afmeldenLes(student, les, status, reden));
						} catch(Exception e) {
							e.printStackTrace();
						}
						break;
				}
				break;
			
			case "docent":
				Docent docent = null;
				try {
					String gebruikersnaam = parameters[1];
					
					docent = this.opleiding.getDocentMetGebruikersnaam(gebruikersnaam);
				} catch (Exception e) {
					this.handleError(101);
					
					return;
				}
				
				switch (parameters[2]) {
					case "rooster":
						job.add("rooster", new RoosterController(opleiding).roosterDocent(docent));
						break;
				}
				break;
			
			case "les":
				Les les = null;
				try {					
					LocalDateTime begintijd = LocalDateTime.parse(parameters[2]);
					String lokaal = parameters[1];
					
					les = this.opleiding.getLes(begintijd, lokaal);
				} catch (Exception e) {
					e.printStackTrace();
					this.handleError(-1);
					
					return;
				}
				
				switch(parameters[3]) {
					case "studenten":
						job.add("studenten", new LesController(opleiding).getStudenten(les));	
						job.add("presentieStatussen", new PresentieController(opleiding).getStatussen());
						break;
					
					case "absenties":
						JsonObject json =(JsonObject) conversation.getRequestBodyAsJSON();
						new LesController(opleiding).setAanwezig(les, json);
						break;
				}
				
			default:
				break;
		}
		
		job.add("code", 0);
		
		conversation.sendJSONMessage(job.build().toString());
	}

	public void handleError(int foutcode) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		String message = "";
		switch(foutcode) {
			case 101:
				message = "Geen gebruikersnaam";
				break;
				
			default:
				message = "[onbekend]";
				break;
		}
		
		job.add("code", foutcode);
		job.add("message", message);
		
		this.conversation.sendJSONMessage(job.build().toString());
	}
}
