package july13th;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** LocalDate : date without time**/
public class LocalDate_Example {
	public static void main(String[] args) {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		
		LocalDate localDateParse = LocalDate.parse("1989-06-26");
		System.out.println(localDateParse);
		DayOfWeek ob = localDateParse.getDayOfWeek();
		System.out.println("my birth day : "+ob);

		
		boolean before = localDateParse.isBefore(localDate);
		System.out.println(before);
		
		LocalDate myBirthNextDay = localDateParse.plus(1, ChronoUnit.DAYS);
		System.out.println(myBirthNextDay);
		
	}
}
/**
2020-07-13
1989-06-26
my birth day : MONDAY
true
1989-06-27

*/