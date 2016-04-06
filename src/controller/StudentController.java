package controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Les;
import model.Opleiding;
import model.Student;

public class StudentController {
	private Opleiding opleiding;

	public StudentController(Opleiding infoSys) {
		this.opleiding = infoSys;
	}
	
	public JsonArrayBuilder mijnMedestudenten(Student studentZelf) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		ArrayList<Student> studenten = this.opleiding.getStudentenVanKlas(studentZelf.getKlas());
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
	
	public JsonArrayBuilder afmeldenLes(Student student, Les les, boolean status, String reden) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		les.wijzigPresentieAfmelden(student, status, reden);
		
		jab.add(Json.createObjectBuilder()
			.add("klas", Json.createObjectBuilder()
					.add("code", student.getKlas().getCode())
					)
			.add("docent", Json.createObjectBuilder()
					.add("volledigeNaam", les.getDocent().getVolledigeNaam())
					)
			.add("vak", Json.createObjectBuilder()
					.add("code", les.getVak().getCode())
					)
			.add("begintijd", les.getBegintijd().toString())
			.add("eindtijd", les.getEindtijd().toString())
			.add("lokaal", les.getLokaal())
			.add("aanwezig", les.getPresentieVanStudent(student).getAanwezigString())
			.add("afgemeld", les.getPresentieVanStudent(student).getAfgemeldString())
			);
		
		this.opleiding.presentiesOpslaan();
		return jab;
	}
}
