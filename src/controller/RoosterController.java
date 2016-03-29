package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import model.Klas;
import model.Opleiding;
import model.Student;
import model.Vak;
import server.Conversation;
import server.Handler;

public class RoosterController implements Handler {
	private Opleiding informatieSysteem;
	
	public RoosterController(Opleiding infoSys)	{
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/student/mijnrooster")) {
			mijnRooster(conversation);
		}
	}
	
	private void mijnRooster(Conversation conversation)	{
		System.out.println("RoosterController");
		
		JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		String gebruikersnaam = jsonObjectIn.getString("username");
		
		Student student = informatieSysteem.getStudent(gebruikersnaam);			// Student-object opzoeken
		ArrayList<Vak> vakken = student.getVakken();
		
		String klasCode = null;
		
		ArrayList<Klas> klassen = informatieSysteem.getKlassen();
		
		ArrayList<Student> studentenUitKlas;
		outerloop:
		for(Klas k : klassen)	{
			studentenUitKlas = k.getStudenten();
			for(Student s : studentenUitKlas)	{
				if(s.equals(student))	{
					klasCode = k.getKlasCode();
					break outerloop;
				}
			}
		}
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (Vak v : vakken) {
			jab.add(
				Json.createObjectBuilder()							// daarin voor elk vak een JSON-object...
					.add("klascode", klasCode)
					.add("vakcode", v.getVakCode())
					.add("vaknaam", v.getVakNaam())
			);
		}
		
		conversation.sendJSONMessage(jab.build().toString());			// terug naar de Polymer-GUI!
	}
}
