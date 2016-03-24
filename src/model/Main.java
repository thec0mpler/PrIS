package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
	public static void main(String[] args) throws Exception {
		PrIS pis = new PrIS();
		pis.importeerKlassen()";"
		pis.importRooster("rooster_C.csv");
	}

	public static void importRooster(String file) throws Exception {
		FileReader fis = new FileReader(file);
		BufferedReader br = new BufferedReader(fis);

		String regel = br.readLine();
		while (regel != null) {
			String[] values = regel.split(",");

			String[] datum = values[0].split("-");
			int jaar = Integer.parseInt(datum[0]);
			int maand = Integer.parseInt(datum[1]);
			int dag = Integer.parseInt(datum[2]);

			String[] begintijd = values[1].split(":");
			int begintijdUur = Integer.parseInt(begintijd[0]);
			int begintijdMinuten = Integer.parseInt(begintijd[1]);

			String[] eindtijd = values[2].split(":");
			int eindtijdUur = Integer.parseInt(eindtijd[0]);
			int eindtijdMinuten = Integer.parseInt(eindtijd[1]);

			LocalDateTime startdatum = LocalDateTime.of(LocalDate.of(jaar, maand, dag),
					LocalTime.of(begintijdUur, begintijdMinuten));
			LocalDateTime einddatum = LocalDateTime.of(LocalDate.of(jaar, maand, dag),
					LocalTime.of(eindtijdUur, eindtijdMinuten));
			String vakCode = values[3];
			String docent = values[4];
			String lokaal = values[5];
			String klas = values[6];

			regel = br.readLine();
		}

		br.close();
	}
}
