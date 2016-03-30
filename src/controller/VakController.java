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

public class VakController implements Handler {
	private Opleiding informatieSysteem;
	
	public VakController(Opleiding infoSys)	{
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/vakken")) {
			vakken(conversation);
		}
	}
	
	private void vakken(Conversation conversation) {
		System.out.println("VakkenController");
		JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		
		String gebruikersnaam = null;
		Student student = null;
		try {
			jsonObjectIn.getString("username");
			student = this.informatieSysteem.getStudent(gebruikersnaam);
		} catch (Exception e) {
			// Nothing
		}
		
		String vak = null;
		try {
			vak = jsonObjectIn.getString("vak");
		} catch (Exception e) {
			// Nothing
		}
		
		boolean les = false;
		try {
			les = jsonObjectIn.getBoolean("lessen");
		} catch (Exception e) {
			// Nothing
		}
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		if (vak == null) {
			ArrayList<Vak> vakken = student.getVakken();
			
			for (Vak v : vakken) {
				jab.add(
					Json.createObjectBuilder()
						.add("vakcode", v.getVakCode())
						.add("vaknaam", v.getVakNaam())
				);
			}
		} else if (les == true) {
			Vak selectedVak = null;
			for (Vak vak2 : student.getVakken()) {
				if (vak2.getVakCode().equals(vak)) {
					selectedVak = vak2;
					break;
				}
			}
			
			if (selectedVak != null) {
				ArrayList<Les> lessen = selectedVak.getLessen();
				
				for (Les les2 : lessen) {
					jab.add(
						Json.createObjectBuilder()
							.add("beginTijd", "(begintijd)")
							.add("eindTijd", "(eindtijd)")
					);
				}
			}
		}
		
		conversation.sendJSONMessage(jab.build().toString());
	}
}
