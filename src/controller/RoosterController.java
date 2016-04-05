package controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import model.Les;
import model.Opleiding;
import model.Student;

public class RoosterController extends Controller {
	public RoosterController(Opleiding opleiding) {
		super(opleiding);
	}

	public JsonArrayBuilder roosterStudent(Student student) {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		ArrayList<Les> lessen = this.opleiding.getLessenVanStudent(student);
		Collections.sort(lessen, (o1, o2) -> o1.getBegintijd().compareTo(o2.getBegintijd()));
		
		try {			
			for (Les les : lessen) {				
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
						.add("afgemeldReden", les.getPresentieVanStudent(student).getAfgemeldReden())
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.handleError(104);
		}
		
		return jab;
	}
}
