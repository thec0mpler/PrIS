package controller;

import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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

		int i = 1;
		for (String s : parameters) {
			System.out.print("" + i + ": ");
			System.out.println(s);
			i++;
		}
		
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
						System.out.println("VakController.vakken");
						job.add("vakken", new VakController(opleiding).vakkenStudent(student));
						break;
					
					case "rooster":
						System.out.println("RoosterController.rooster");
						job.add("rooster", new RoosterController(opleiding).roosterStudent(student));
						break;	
					
					case "medestudenten":
						System.out.println("StudentenController.medestudenten");
						job.add("medestudenten", new StudentController(opleiding).mijnMedestudenten(student));
						break;
					
					case "afmelden":
						System.out.println("StudentenController.afmeldenLes");
						
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
