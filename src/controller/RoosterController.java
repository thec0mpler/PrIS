package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import model.Klas;
import model.Les;
import model.Opleiding;
import model.Student;
import model.Vak;
import server.Conversation;
import server.Handler;

public class RoosterController extends Controller implements Handler {
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

		JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		
		String gebruikersnaam = null;
		try	{
			gebruikersnaam = jsonObjectIn.getString("username");
		}	catch(Exception ex)	{
			this.handleError(conversation, 101);
			System.out.print("Inkomend JsonObject heeft geen \"username\"");
		}
		
		Student student = null;
		Klas klas = null;
		
		try {
			student = informatieSysteem.getStudent(gebruikersnaam);			// Student-object opzoeken
			klas = informatieSysteem.getKlasBijStudent(student);
		}	catch(Exception ex)	{
			this.handleError(conversation, 102);
			System.out.println("Kan klas bij Student niet vinden");
		}
		
		ArrayList<Vak> vakken = null;
		try	{
			vakken = student.getVakken();
		}	catch(Exception ex)	{
			this.handleError(conversation, 103);
			System.out.println("Kan vakken bij student niet vinden");
		}
		
		try	{
			JsonArrayBuilder jab = Json.createArrayBuilder();
			
			for (Vak v : vakken) {
				for(Les l : v.getLessen())	{
					jab.add(
							Json.createObjectBuilder()							// daarin voor elk vak een JSON-object...
								.add("klascode", klas.getKlasCode())
								.add("vakcode", v.getVakCode())
								.add("vaknaam", v.getVakNaam())
								.add("begintijd", l.getBeginTijd().toString())
								.add("eindtijd", l.getEindTijd().toString())
						);
				}
			}
			
			conversation.sendJSONMessage(jab.build().toString());				// terug naar de Polymer-GUI!
		}	catch(Exception ex)	{
			this.handleError(conversation, 104);
		}
		
	}
}
