package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Opleiding;
import model.Student;
import model.Vak;

public class VakController extends Controller {
	
	public VakController(Opleiding opleiding) {
		super(opleiding);
	}
	
	public JsonArrayBuilder vakken(String gebruikersnaam) {
		System.out.println("VakkenController");
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		Student student = this.opleiding.getStudent(gebruikersnaam);
		System.out.println(student);
		ArrayList<Vak> vakken = student.getVakken();
		
		for (Vak v : vakken) {
			jab.add(
				Json.createObjectBuilder()
					.add("vakcode", v.getVakCode())
//					.add("vaknaam", v.getVakNaam())
			);
		}
		
		return jab;
	}
}
