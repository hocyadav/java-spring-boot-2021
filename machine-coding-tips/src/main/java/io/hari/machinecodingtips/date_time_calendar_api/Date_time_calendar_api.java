package io.hari.machinecodingtips.date_time_calendar_api;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

public class Date_time_calendar_api {

    public static void main(String[] args) {

        // 1 local date

        LocalDate today = LocalDate.now();
        System.out.println("today = " + today);

        // crud
        int year = today.getYear();
        Month month = today.getMonth();
        int dayOfMonth = today.getDayOfMonth();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println("year = " + year);
        System.out.println("month = " + month + " , month value " + month.getValue());
        System.out.println("dayOfMonth = " + dayOfMonth);
        System.out.println("dayOfWeek = " + dayOfWeek + " , dayOfWeek value " + dayOfWeek.getValue());

        //set operation
        LocalDate lastMonthSameDay = today.minus(1, ChronoUnit.MONTHS);
        System.out.println("\nlastMonthSameDay = " + lastMonthSameDay);

        LocalDateTime todayMidNight = today.atStartOfDay();
        System.out.println("localDateTime = " + todayMidNight);

        LocalDate tomorrow = today.plusDays(1);
        boolean before = today.isBefore(tomorrow);
        System.out.println("before = " + before);

        boolean after = today.isAfter(tomorrow);
        System.out.println("after = " + after);

        LocalDate todayFirstMonthDate = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("todayFirstMonthDate = " + todayFirstMonthDate);

        LocalDate todayLastMonthDate = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("todayLastMonthDate = " + todayLastMonthDate);

        LocalDate todayFirstYearDate = today.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("todayFirstYearDate = " + todayFirstYearDate);

        int lengthOfMonth = today.lengthOfMonth();
        System.out.println("lengthOfMonth = " + lengthOfMonth);

        int lengthOfYear = today.lengthOfYear();
        System.out.println("lengthOfYear = " + lengthOfYear);

        LocalDate min = LocalDate.MIN;
        System.out.println("min = " + min);

        LocalDate max = LocalDate.MAX;
        System.out.println("max = " + max);

        String todayFormat = today.format(DateTimeFormatter.ISO_DATE);
        System.out.println("todayFormat = " + todayFormat);
        String todayFormat2 = today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("todayFormat2 = " + todayFormat2);
        String todayFormat3 = today.format(DateTimeFormatter.ofPattern("yyyy MM dd"));
        System.out.println("todayFormat3 = " + todayFormat3);
        String format = today.format(DateTimeFormatter.ofPattern("yyyy--MM-dd"));
        System.out.println("format = " + format);

        System.out.println("format1 = " + today.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("format2 = " + today.format(DateTimeFormatter.BASIC_ISO_DATE));


        // 2. local date time (it will not store time zone/offset i.e. +5.30, for this we can use offsetDate time)
        // same feature we can achieve as local date {check Local date time interface and Local date time interface}
        //LocalDate     implements Temporal, TemporalAdjuster, ChronoLocalDate,
        //LocalDateTime implements Temporal, TemporalAdjuster, ChronoLocalDateTime<LocalDate> (NOTE : ChronoLocalDateTime used ChronoLocalDate >> interface ChronoLocalDateTime<D extends ChronoLocalDate> )

        LocalDateTime todayDateTime = LocalDateTime.now();
        System.out.println("\ntodayDateTime = " + todayDateTime);


        // 3. zone date time (store date time + zone information, this can be achieve using offset date time)

        ZonedDateTime todayZoneDateTime = ZonedDateTime.now();
        System.out.println("\ntodayZoneDateTime = " + todayZoneDateTime);//2022-08-28T11:07:02.268527

        Set<String> availableZoneIds = ZoneOffset.getAvailableZoneIds();
        System.out.println("availableZoneIds = " + availableZoneIds);

        //4. offset date time
        // create using local date time + zone info +5.30

        ZoneOffset zoneOffset = ZoneOffset.of("+05:30");
        System.out.println("zoneOffset = " + zoneOffset);

        OffsetDateTime todayOffsetDateTime = OffsetDateTime.of(todayDateTime, zoneOffset);//it will just add +5.30 in the end
        System.out.println("todayOffsetDateTime = " + todayOffsetDateTime);//2022-08-28T11:07:02.268527+05:30 (local date time value : 2022-08-28T11:07:02.268527)

        //5. Period and Duration class
        //Period == quantity of time == (in terms of year, month, days) == all setter method are of year, month, day
        //Duration == quantity of time == (in terms of seconds, nano sec) == all setter method are hr, min, sec

        Period amountToAdd = Period.ofDays(1);
        LocalDateTime localDateTime = todayDateTime.plus(amountToAdd);
        System.out.println("localDateTime = " + localDateTime);

        Duration duration = Duration.ofHours(1);
        LocalDateTime localDateTime1 = localDateTime.plus(duration);
        System.out.println("localDateTime1 = " + localDateTime1);

        // 6. Cronounit class
        //between method accept Temporal class instance, (and Local date time, Offset date time and Zonal date time, they all impl Temporal class)
        long between = ChronoUnit.DAYS.between(today, tomorrow);
        System.out.println("between = " + between);

        long between1 = ChronoUnit.DAYS.between(OffsetDateTime.now(), OffsetDateTime.now().plusDays(1));
        System.out.println("between1 = " + between1);

        long between2 = ChronoUnit.DAYS.between(ZonedDateTime.now(), ZonedDateTime.now().plusDays(1));
        System.out.println("between2 = " + between2);

        //7. convert old date , calendar, epoc time into local date time
        // Date == from java 1.1
        // local date time, offset date time, zonal date time == from java 1.8
        Date date = new Date();
        System.out.println("date = " + date);

        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date.toInstant(), zoneOffset);
        System.out.println("localDateTime2 = " + localDateTime2);
        OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(date.toInstant(), zoneOffset);
        System.out.println("offsetDateTime = " + offsetDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), zoneOffset);
        System.out.println("zonedDateTime = " + zonedDateTime);

        //8 convert from calendar to local date
        Calendar calendar = Calendar.getInstance();
        System.out.println("\ncalendar = " + calendar);
        Date dateFromCalendar = calendar.getTime();
        System.out.println("dateFromCalendar = " + dateFromCalendar);

        LocalDateTime localDateTime3 = LocalDateTime.ofInstant(calendar.toInstant(), zoneOffset);
        System.out.println("localDateTime3 = " + localDateTime3);
        OffsetDateTime offsetDateTime1 = OffsetDateTime.ofInstant(calendar.toInstant(), zoneOffset);
        System.out.println("offsetDateTime1 = " + offsetDateTime1);
        ZonedDateTime zonedDateTime1 = ZonedDateTime.ofInstant(calendar.toInstant(), zoneOffset);
        System.out.println("zonedDateTime1 = " + zonedDateTime1);

        // https://www.benchresources.net/java-8-how-to-convert-offsetdatetime-to-calendar-and-vice-versa/

        //9 convert java 1.8 offset date time to old calendar
        calendar.setTime(date);
        System.out.println("calendar = " + calendar);

        // convert offset date time to calendar : offset -> get instance -> date -> calendar
        // convert calendar to offset : get zone id , get instance , create offset

        //10 calendar CRUD : https://www.javatpoint.com/java-util-calendar

        Date calendarTime = calendar.getTime();
        System.out.println("calendarTime = " + calendarTime);
        String calendarType = calendar.getCalendarType();
        System.out.println("calendarType = " + calendarType);
        TimeZone calendarTimeZone = calendar.getTimeZone();
        System.out.println("calendarTimeZone = " + calendarTimeZone);
        int calendarWeekYear = calendar.getWeekYear();
        System.out.println("calendarWeekYear = " + calendarWeekYear);
        boolean after1 = calendar.after(calendar);
        System.out.println("after1 = " + after1);

        calendar.add(Calendar.DATE, -15);
        Date dateObjFromCalendar = calendar.getTime();
        System.out.println("15 day ago = " + dateObjFromCalendar);
        calendar.add(Calendar.HOUR, 2);
        System.out.println("add 2 hr = " + dateObjFromCalendar);
        calendar.add(Calendar.HOUR, -2);
        System.out.println("minus 2 hr = " + dateObjFromCalendar);

        calendar.add(Calendar.MONTH, +2);
        System.out.println("add 2 month = " + dateObjFromCalendar);

        calendar.add(Calendar.YEAR, +2);
        System.out.println("add 2 year = " + dateObjFromCalendar);

        calendar.add(Calendar.MINUTE, +90);
        System.out.println("add 90 min = " + dateObjFromCalendar);
    }
}
