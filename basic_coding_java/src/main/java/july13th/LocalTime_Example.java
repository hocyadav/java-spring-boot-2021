package july13th;

import java.time.LocalTime;

/**LocaTime : time without date**/
public class LocalTime_Example {
	public static void main(String[] args) {
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);
		
		LocalTime of = LocalTime.of(6, 20);//using factory method
		System.out.println(of);
		
		LocalTime localTime2 = LocalTime.parse("18:07:57");//hr min sec, all 3 required
		System.out.println(localTime2);

		int hour = of.getHour();
		System.out.println(hour);
		
	}
}
/**
18:28:24.991
06:20
18:07:57
6


*/