package july13th;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/** Local Date time implementation for machine coding **/
public class LocalDateTime_Ex {
	
	public static void main(String[] args) {
		LocalDateTime localDt = LocalDateTime.now();
		System.out.println(localDt);
		
		splitNMerge(localDt);
		
		//use this one in system design
		String format = localDt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));//good
		System.out.println(format);
		
		String format3 = localDt//input localdatetime object 
							.plusDays(2) //add days..this is like builder design pattern
							.plusHours(1)//add time..its like lambda
							.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));//good
		System.out.println(format3);
		
		
		String format2 = localDt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
		System.out.println(format2);
	}

	//input : 2020-07-13T18:14:50.362 --> output : 2020-07-13 18:14:50.362
	private static void splitNMerge(LocalDateTime localDt) {
		//in between T is there, split and store in array
		//then join with space
		String[] split = localDt.toString().split("T");
		for (String str : split) {
			System.out.println(str);
		}
		String join = String.join(" ", split);
		System.out.println(join);//2020-07-13 18:14:50.362
	}
}

/*
2020-07-13T18:23:54.808
2020-07-13
18:23:54.808
Jul 13, 2020 6:23:54 PM
Jul 15, 2020 7:23:54 PM
7/13/20 6:23 PM
 */