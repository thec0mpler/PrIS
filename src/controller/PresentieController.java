package controller;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Opleiding;
import model.PresentieStatussen;

public class PresentieController extends Controller {
	public PresentieController(Opleiding opleiding)	{
		super(opleiding);
	}
	
	public JsonArrayBuilder getStatussen() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (PresentieStatussen status : PresentieStatussen.values()) {
			String naam = "";
			
			switch(status) {
				case ONBEKEND:
					// naam = "Onbekend";
					continue;
				case AANWEZIG:
					naam = "Aanwezig";
					break;
				case GEOORLOOFDAFWEZIG:
					naam = "Geoorloofd afwezig";
					break;
				case AFWEZIG:
					naam = "Afwezig";
					break;
			}
			
			jab.add(Json.createObjectBuilder()
					.add("nummer", status.getInt())
					.add("naam", naam)
					);
		}
		
		return jab;
	}
}
