package org.vtiger.practice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
	public static void main(String[] args) {
	
	String s = "24-05-1992";
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate month = LocalDate.parse(s, dtf);
		System.out.println(month.getMonth());
	
}
}
