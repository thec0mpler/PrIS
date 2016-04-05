package controller;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Opleiding;

public class DocentController extends Controller {
	public DocentController(Opleiding opleiding) {
		super(opleiding);
	}
		
	public JsonArrayBuilder mijnVakken() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		// TODO
		
		return jab;
	}
}
