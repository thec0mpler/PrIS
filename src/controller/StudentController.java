package controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Opleiding;
import model.Student;

public class StudentController {
	private Opleiding informatieSysteem;

	public StudentController(Opleiding infoSys) {
		this.informatieSysteem = infoSys;
	}

	public JsonArrayBuilder mijnMedestudenten(Student studentZelf) {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		ArrayList<Student> studenten = this.informatieSysteem.getStudentenVanKlas(studentZelf.getKlas());
		Collections.sort(studenten, (o1, o2) -> o1.getVolledigeNaam().compareTo(o2.getVolledigeNaam()));

		for (Student student : studenten) {
			if (!student.equals(studentZelf)) {
				jab.add(Json.createObjectBuilder()
						.add("nummer", student.getStudentnummer())
						.add("gebruikersnaam", student.getGebruikersNaam())
						.add("voornaam", student.getVoornaam())
						.add("tussenvoegsel", student.getTussenvoegsel())
						.add("achternaam", student.getAchternaam())
						.add("volledigeNaam", student.getVolledigeNaam()));
			}
		}

		return jab;
	}
}
