package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.regex.Pattern;

import model.Opleiding;
import server.JSONFileServer;

public class Application {
	/**
	 * Deze klasse is het startpunt voor de applicatie. Hierin maak je een server 
	 * op een bepaalde poort (bijv. 8888). Daarna is er een PrIS-object gemaakt. Dit
	 * object fungeert als toegangspunt van het domeinmodel. Hiervandaan kan alle
	 * informatie bereikt worden.
	 * 
	 * Om het domeinmodel en de Polymer-GUI aan elkaar te koppelen zijn diverse controllers
	 * gemaakt. Er zijn meerdere controllers om het overzichtelijk te houden. Je mag zoveel
	 * controller-klassen maken als je nodig denkt te hebben. Elke controller krijgt een
	 * koppeling naar het PrIS-object om benodigde informatie op te halen.
	 * 
	 * Je moet wel elke URL die vanaf Polymer aangeroepen kan worden registreren! Dat is
	 * hieronder gedaan voor een drietal URLs. Je geeft daarbij ook aan welke controller
	 * de URL moet afhandelen.
	 * 
	 * Als je alle URLs hebt geregistreerd kun je de server starten en de applicatie in de
	 * browser opvragen! Zie ook de controller-klassen voor een voorbeeld!
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException {		
		JSONFileServer server = new JSONFileServer(new File("webapp/app"), 80);
		Opleiding infoSysteem = new Opleiding("ICT");
		
		server.registerHandler("/api", new Controller(infoSysteem));
		server.start();
		
//		String gebruikersnaam = "SerkanSevinç"; 
//		
//		String nfdNormalizedString = Normalizer.normalize(gebruikersnaam, Normalizer.Form.NFD); 
//	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//		
//	    System.out.println(pattern.matcher(nfdNormalizedString).replaceAll(""));
//	    
//		System.out.println(gebruikersnaam);
	}
}

