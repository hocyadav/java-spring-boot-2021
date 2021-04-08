package com.hari.springsecurity.resource;

import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.*;

/**
 * @author HariomYadav
 * @since 26/01/21
 */
//@RestController //for api end points
@Controller //for thymeleaf view endpoints
public class HomeResource {
    //return html page from resource class
//    @GetMapping("/")
//    public String home() {
//        extracted();
//        return "<h1> welcome </h1>";
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        extracted();
//        return "<h1> welcome user</h1>";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        extracted();
//        return "<h1> welcome admin</h1>";
//    }

    @GetMapping("/")
    public String home(Model model) {
        final Authentication authentication = getAuthentication();
        model.addAttribute("name", authentication.getName());
        return "home-page";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("name", getAuthentication().getName());
        return "user-page";
    }

    @GetMapping("/admin")
    public String admin() {
//        model.addAttribute("name", getAuthentication().getName());//use thymeleaf extra security dependency to get auth obj
        return "admin-page";
    }

    @GetMapping("/chart1")
    public String googleChartData() {
        getAuthentication();
        return "chart1-page";
    }

    //working
    @GetMapping("/chart1-inline")
    public String readControllerdata_thymeLeafStyle(Model model) {
        getAuthentication();
        model.addAttribute("all_data", getData());
        return "chart1-inline";
    }

    @GetMapping("/chart2")
    public String googleChartData2(Model model) {
        getAuthentication();
        model.addAttribute("all_data", getData());
        return "chart-new";
    }

    @GetMapping("/chart3")
    public String googleChartData3Map(Model model) {
        getAuthentication();
        model.addAttribute("chartData", getData());
        return "chart-new-map";
    }

    @GetMapping("/map1")
    public String mapTesting() {//working
        return "map-test-hardcoded-data";
    }

    @GetMapping("/map2")
    public String mapTesting2(Model model) {//working
        model.addAttribute("all_data", getAllData());
//        model.addAttribute("all_data", getAllDataState());//not working
        return "map-world-data";
    }

    @GetMapping("/map3")
    public String mapTesting3(Model model) {
        model.addAttribute("all_data", getAllDataState());
        return "map-state-data";
    }

    @GetMapping("/ptime1")
    public String popularityTime(Model model) {//working - create 2 values graphs - but here it is based start time 0
        model.addAttribute("all_data", getAllPopularityTimeData());
        return "popularity-time";
    }

    //https://developers.google.com/chart/interactive/docs/gallery/annotationchart
    @GetMapping("/timeline")
    public String timelineChart(Model model) {//working
        model.addAttribute("all_data", getAllTimeLineData());
        return "timeline-chart";
    }

    //what i need to create this data
    //1st day wise data : date + that day cases + that day cure + that day death
    //day wise top news
    private List<TimeLineEntity> getAllTimeLineData() {
        List<TimeLineEntity> list = new ArrayList<>();
        list.add(getTimeLineEntity(2020, 1, 1, 200,"start title1", "text1", 300,"start title2", "text2"));
        list.add(getTimeLineEntity(2020, 2, 10, 400,"start -- title2", "text2--", 500,"start title2 --", "text2--"));
        list.add(getTimeLineEntity(2020, 3, 10, 400,"ndtc news", "news news ... ", 500,"news based on this value", "news based on this value"));
        list.add(getTimeLineEntity(2020, 3, 20, 500,null, null, 500,null, null));
        list.add(getTimeLineEntity(2020, 3, 25, 600,"lockdown started", null, 700,null, null));
        list.add(getTimeLineEntity(2020, 3, 30, 60000,null, null, 40000,"lockdown end", null));
        return list;
    }

    private TimeLineEntity getTimeLineEntity(int year, int month, int dayOfMonth, Integer value1, String start_title1, String text1, Integer value2, String start_title2, String text2) {
        TimeLineEntity e1 = TimeLineEntity.builder().date(LocalDate.of(year, month, dayOfMonth))
                .entity1(Entity.builder().value(value1).title(start_title1).text(text1).build())
                .entity2(Entity.builder().value(value2).title(start_title2).text(text2).build())
                .build();
        return e1;
    }

    private List<Popularity> getAllPopularityTimeData() {
        List<Popularity> popularities = new ArrayList<>();
        popularities.add(Popularity.builder().inDays(0).value1(0).value2(0).build());
        popularities.add(Popularity.builder().inDays(1).value1(10).value2(0).build());
        popularities.add(Popularity.builder().inDays(2).value1(23).value2(15).build());
        popularities.add(Popularity.builder().inDays(3).value1(17).value2(9).build());
        popularities.add(Popularity.builder().inDays(4).value1(18).value2(10).build());
        popularities.add(Popularity.builder().inDays(5).value1(19).value2(10).build());
        popularities.add(Popularity.builder().inDays(6).value1(26).value2(10).build());
        popularities.add(Popularity.builder().inDays(7).value1(89).value2(10).build());
        popularities.add(Popularity.builder().inDays(15).value1(18).value2(10).build());
        return popularities;
    }

    private Map<Integer, Integer> getPopularityValue(int i, int i1) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(i, i1);
        return map;
    }

    private Map<String, Map<String, Object>> getAllDataState() {
        Map<String, Map<String, Object>> map = new LinkedHashMap<>();
        map.putIfAbsent("State Code", getValue("State", "Population or cases"));//add this in js as contants global
        map.putIfAbsent("IN-UT", getValue("Uttarakhand", 1314));
        map.putIfAbsent("IN-CT", getValue("Chhattisgarh", 314));
        map.putIfAbsent("IN-KA", getValue("Karnataka", 1231214));
        map.putIfAbsent("IN-MN", getValue("Manipur", 114));
        map.putIfAbsent("IN-PB", getValue("Panjab", 1243));
        return  map;
    }

    private LinkedHashMap<String, Object> getValue(String uttarakhand, Object value1) {
        final LinkedHashMap<String, Object> value = new LinkedHashMap<>();
        value.put(uttarakhand, value1);
        return value;
    }

    private Map<String, Object> getAllData() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Country", "Popularity");//add this in js as contants global
        map.put("Germany", 200);
        map.put("United States", 200);
        map.put("Brazil", 500);
        map.put("Canada", 210);
        map.put("France", 230);
        map.put("Germany", 300);
        map.put("India", 900);
        return  map;
    }

    private Map<String, Integer> getData() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Mushrooms", 12);
        map.put("Onion", 2);
        map.put("Olives", 2);
        map.put("Zucchini", 2);
        map.put("Pepperoni", 2);
        map.put("hariom", 2);
        map.put("yadav Pepperoni", 2);
        map.put("2Pepperoni", 2);
        map.put("3Pepperoni", 2);
        map.put("4Pepperoni", 2);
        return map;
    }

    private Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth.getPrincipal() = " + auth.getPrincipal());
        System.out.println("auth.getName() = " + auth.getName());
        System.out.println("auth.getDetails() = " + auth.getDetails());
        System.out.println("auth.getAuthorities() = " + auth.getAuthorities());
        System.out.println("auth.getCredentials() = " + auth.getCredentials());
        return auth;
    }
}

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
class Popularity {
    int inDays;
    int value1;
    int value2;
}

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
class TimeLineEntity {
    LocalDate date;
    Entity entity1;
    Entity entity2;
}

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
class Entity{
    Integer value;
    String title;
    String text;
}

