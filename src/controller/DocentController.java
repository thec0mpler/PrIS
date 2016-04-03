package controller;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Opleiding;

public class DocentController {
	private Opleiding opleiding;
	
	public DocentController(Opleiding opleiding) {
		this.opleiding = opleiding;
	}
		
	public JsonArrayBuilder mijnVakken() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		// TODO
		
		return jab;
	}
}
