package io.hari.machinecodingtips.simpletest;

import java.time.Instant;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class TestOffsetDateTime {

    public static void main(String[] args) {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime = " + offsetDateTime);
        Month month = offsetDateTime.getMonth();
        int dayOfMonth = offsetDateTime.getDayOfMonth();//today 11th
        int hour = offsetDateTime.getHour();
        int minute = offsetDateTime.getMinute();
        int year = offsetDateTime.getYear();
        System.out.println("year = " + year);
        System.out.println("month = " + month);
        System.out.println("dayOfMonth = " + dayOfMonth);
        System.out.println("hour = " + hour);
        System.out.println("minute = " + minute);

        Date date = new Date();
        System.out.println("date = " + date);

        //date to offset datetime
        OffsetDateTime offsetDateTime_ = date.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("offsetDateTime_ = " + offsetDateTime_);

        //offset datetime to date
        Instant instant = offsetDateTime_.toInstant();
        System.out.println("instant = " + instant);
        Date date1 = new Date(instant.toEpochMilli());
        System.out.println("date1 = " + date1);
    }
}
