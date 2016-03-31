package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import model.Klas;
import model.Opleiding;
import model.Student;
import server.Conversation;
import server.Handler;

public class StudentController {
	private Opleiding informatieSysteem;
	
	/**
	 * De StudentController klasse moet alle student-gerelateerde aanvragen
	 * afhandelen. Methode handle() kijkt welke URI is opgevraagd en laat
	 * dan de juiste methode het werk doen. Je kunt voor elke nieuwe URI
	 * een nieuwe methode schrijven.
	 * 
	 * @param infoSys - het toegangspunt tot het domeinmodel
	 */
	public StudentController(Opleiding infoSys) {
		informatieSysteem = infoSys;
	}

	/**
	 * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
	 * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
	 * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
	 * 
	 * @param conversation - alle informatie over het request
	 * @return 
	 */
	public JsonArrayBuilder mijnMedestudenten(Student student) {
		
		//String klasCode = student.getMijnKlas().getKlasCode();					// klascode van de student opzoeken
		
		Klas klas = informatieSysteem.getKlasBijStudent(student);
		
		ArrayList<Student> studentenVanKlas = informatieSysteem.getStudentenVanKlas(klas.getKlasCode());	// medestudenten opzoeken
		
		JsonArrayBuilder jab = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...
		
		for (Student s : studentenVanKlas) {									// met daarin voor elke medestudent een JSON-object... 
			if (s.getGebruikersNaam().equals(student.getGebruikersNaam())) 					// behalve de student zelf...
				continue;
			else {
				jab.add(Json.createObjectBuilder()
						.add("naam", s.getGebruikersNaam()));
			}
		}
		return jab;
	}
}
