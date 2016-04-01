package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
	public static void main(String[] args) throws Exception {
		LocalDateTime begintijd = LocalDateTime.of(LocalDate.of(2016, 4, 1),
				LocalTime.of(9, 30));
		LocalDateTime eindtijd = LocalDateTime.of(LocalDate.of(2016, 4, 1),
				LocalTime.of(10, 30));
		Vak vak = new Vak("vakcode");
		
		Les les = new Les(vak, begintijd, eindtijd, "lokaal");
		System.out.println(les);
	}
}
