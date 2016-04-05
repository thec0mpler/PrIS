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

	public JsonArrayBuilder vakkenStudent(Student student) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		ArrayList<Vak> vakken = this.opleiding.getVakken(); // Should be from student
		
		for (Vak v : vakken) {
			jab.add(Json.createObjectBuilder().add("vakcode", v.getCode()));
		}
		
		return jab;
	}
}
