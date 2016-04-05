package controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

import model.Les;
import model.Opleiding;
import model.Presentie;
import model.PresentieStatussen;
import model.Student;

public class LesController extends Controller {
	public LesController(Opleiding opleiding) {
		super(opleiding);
	}
	
	public JsonArrayBuilder getStudenten(Les les) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		ArrayList<Student> studenten = this.opleiding.getStudentenVanLes(les);
		Collections.sort(studenten, (o1, o2) -> o1.getVolledigeNaam().compareTo(o2.getVolledigeNaam()));
		
		for (Student student : studenten) {
			Presentie presentie = les.getPresentieVanStudent(student);
			int aanwezigSelected = PresentieStatussen.AANWEZIG.getInt();
			
			if (presentie.getAanwezig() != PresentieStatussen.ONBEKEND) {
				aanwezigSelected = presentie.getAanwezigInt();
			} else if (presentie.getAfgemeld()) {
				aanwezigSelected = PresentieStatussen.AFWEZIG.getInt();
			}
			
			jab.add(Json.createObjectBuilder()
					.add("gebruikersnaam", student.getGebruikersNaam())
					.add("volledigeNaam", student.getVolledigeNaam())
					.add("aanwezig", les.getPresentieVanStudent(student).getAanwezigString())
					.add("aanwezigSelected", aanwezigSelected)
					.add("afgemeld", les.getPresentieVanStudent(student).getAfgemeldString())
					.add("afgemeldReden", les.getPresentieVanStudent(student).getAfgemeldReden())
					);
		}
		
		return jab;
	}
	
	public void setAanwezig(Les les, JsonObject json) {
		try {
			JsonArray absenties = json.getJsonArray("absenties");
			
			for (JsonValue absentieItem : absenties) {
				JsonObject absentie = (JsonObject) absentieItem;
				
				String gebruikersnaam = absentie.getString("gebruikersnaam");
				int aanwezig = absentie.getInt("aanwezigSelected");
				
				PresentieStatussen status = PresentieStatussen.ONBEKEND;
				for (PresentieStatussen presentieStatus : PresentieStatussen.values()) {
					if (presentieStatus.getInt() == aanwezig) {
						status = presentieStatus;
						
						break;
					}
				}
				
				Student student = this.opleiding.getStudentMetGebruikersnaam(gebruikersnaam);
				
				if (student != null) {
					les.wijzigPresentieAanwezig(student, status);
				}
			}
			
			this.opleiding.presentiesOpslaan();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
