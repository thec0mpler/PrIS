package controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Docent;
import model.Opleiding;
import model.Student;
import server.Conversation;
import server.Handler;

public class Controller implements Handler {
	protected Opleiding opleiding;
	protected Conversation conversation;

	public Controller(Opleiding opleiding) {
		this.opleiding = opleiding;
		System.out.println("Controller");
	}

	@Override
	public void handle(Conversation conversation) {
		System.out.println("Controller.handle");
		
		this.conversation = conversation;
		String[] parameters = conversation.getParameter("q").split("/");

		int i = 1;
		for (String s : parameters) {
			System.out.print("" + i + ": ");
			System.out.println(s);
			i++;
		}
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		String gebruikersnaam = null;
		String wachtwoord = null;
		
		switch (parameters[0]) {
		case "login":
			JsonObject jsonObjIn = (JsonObject) conversation.getRequestBodyAsJSON();
			
			gebruikersnaam = jsonObjIn.getString("username"); 
			wachtwoord = jsonObjIn.getString("wachtwoord");
			
			String rol = this.opleiding.login(gebruikersnaam, wachtwoord);
			
			job.add("rol", rol);
			
			break;
		case "student":
		case "docent":
			Student student = null;
			Docent docent = null;
			try {
				gebruikersnaam = parameters[1];
				
				if(parameters[0].equals("student"))	{
					student = this.opleiding.getStudent(gebruikersnaam);
				} else if(parameters[0].equals("docent")) {
					docent = this.opleiding.getDocent(gebruikersnaam);
				}
				
				System.out.println("Gebruikersnaam: " + gebruikersnaam);
			} catch (Exception e) {
				this.handleError(101);
				return;
			}
			
			switch (parameters[2]) {
			case "vakken":
				System.out.println("VakController.vakken");
				job.add("vakken", new VakController(opleiding).vakken(gebruikersnaam));
				break;
			
			case "rooster":
				System.out.println("RoosterController.rooster");
				if(student != null)	{
					job.add("rooster", new RoosterController(opleiding).rooster(student));
					break;	
				}	else if(docent != null)	{
					job.add("rooster", new RoosterController(opleiding).rooster(docent));
					break;
				}
			}
			break;
		}
		
		System.out.println("Add code");		
		job.add("code", 0);
		
		conversation.sendJSONMessage(job.build().toString());
	}

	public void handleError(int foutcode) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		String message = null;
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
