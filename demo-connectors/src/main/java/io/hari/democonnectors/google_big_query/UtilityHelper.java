package io.hari.democonnectors.google_big_query;

import java.time.LocalDateTime;

/**
 * @Author Hariom Yadav
 * @create 5/14/2021
 */
public class UtilityHelper {
    public static final String UNDERSCORE = "_";

    public static final String getCurrentTimeAsSuffix() {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder suffix = new StringBuilder();
        suffix.append(now.getMonth()).append(UNDERSCORE)
                .append(now.toLocalDate().getDayOfMonth()).append(UNDERSCORE)
                .append(now.getHour()).append(UNDERSCORE)
                .append(now.getMinute()).append(UNDERSCORE)
                .append(now.getSecond());
        System.out.println("getCurrentTimeAsSuffix = " + suffix);
        return suffix.toString();
    }
}
