package basics;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;

public class DateTimeAPI {

	public static void main(String[] args) {
		duration();
	}

	
	static void duration() {
		LocalTime t1 = LocalTime.now();
		Duration time2hrbefore = Duration.ofHours(2);
		
		System.out.println("time10hrbefore "+ t1.minus(time2hrbefore));
	}
	static void period() {
		// amount of time in terms of years, months, and days
		// Period class can be used with the LocalDate class
		Period p30years = Period.ofYears(30);
		LocalDate birthdate = LocalDate.of(1992, 12, 12);

		System.out.println("yearAt30 " + birthdate.plus(p30years).getYear());

	}

	public static void localDateTimeEx() {
		LocalTime time = LocalTime.of(14, 22);
		LocalDate date = LocalDate.of(2023, Month.JULY, 27);

		LocalDateTime dateTime = LocalDateTime.of(date, time);

		System.out.println("date when program was written : " + dateTime);

		time = LocalTime.now();
		date = LocalDate.now();

		dateTime = LocalDateTime.of(date, time);

		System.out.println("program executed at : " + dateTime);

		// with method
		dateTime = dateTime.withYear(dateTime.getYear() - 1);

		System.out.println("Last year datetime : " + dateTime);

		date = date.withYear(1992).withMonth(Month.DECEMBER.getValue()).withDayOfMonth(12);

		System.out.println("My birthday at " + date);

		// Duration is Time Unit
		LocalTime busDep = LocalTime.of(12, 15); // 12:15
		Duration d1 = Duration.ofMinutes(30); // PT30M
		LocalTime nextBusDep = busDep.plus(d1); // 12:45

		// Period
		LocalDate birthday = LocalDate.of(2020, 10, 23); // 2020-10-23
		Period p1 = Period.ofYears(1); // P1Y
		LocalDate nextBirthday = birthday.plus(p1);
	}

	public static void instant() {
		Instant i1 = Instant.now();
		System.out.println(LocalDateTime.ofInstant(i1, ZoneId.systemDefault()));

		Instant i2 = Instant.now();
		System.out.println("i1 is always before i2 " + i1.isBefore(i2));
	}
}
