package controller;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import model.Opleiding;
import server.Conversation;
import server.Handler;

public class Controller implements Handler {
	protected Opleiding opleiding;

	public Controller(Opleiding opleiding) {
		this.opleiding = opleiding;
		System.out.println("Controller");
	}

	@Override
	public void handle(Conversation conversation) {
		System.out.println("Controller.handle");
		
		String[] parameters = conversation.getParameter("q").split("/");

		int i = 1;
		for (String s : parameters) {
			System.out.print("" + i + ": ");
			System.out.println(s);
			i++;
		}
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		switch (parameters[0]) {
		case "student":
		case "docent":	
			String gebruikersnaam = null;
			try {
				gebruikersnaam = parameters[1];
				System.out.println("Gebruikersnaam: " + gebruikersnaam);
			} catch (Exception e) {
				this.handleError(conversation, 101);
				return;
			}
			
			switch (parameters[2]) {
			case "vakken":
				System.out.println("VakController.vakken");
				job.add("vakken", new VakController(opleiding).vakken(gebruikersnaam));
			}
			break;
		}
		
		System.out.println("Add code");		
		job.add("code", 0);
		
		conversation.sendJSONMessage(job.build().toString());
	}

	public void handleError(Conversation conversation, int foutcode) {
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
		
		conversation.sendJSONMessage(job.build().toString());
	}
}
