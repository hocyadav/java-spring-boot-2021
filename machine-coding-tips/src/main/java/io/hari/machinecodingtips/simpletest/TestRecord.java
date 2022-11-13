package io.hari.machinecodingtips.simpletest;

import lombok.*;

import java.lang.reflect.RecordComponent;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
class VehicleDto{
    String name;
    Integer id;
}
record Vehicle1(String name, Integer id){}
record Vehicle2(String name, Integer id){
    public Vehicle2(String name){
        this(name, null);
    }
}

record Vehicle3(String name, Integer id){
    /**
     * process own fields
     */
    public String toUpperCase(){
        String name1 = name();
        return name1.toUpperCase();
    }
}

record Vehicle4(String name, Integer id) {
    /**
     * Method with different argument
     * process input
     * process own values by calling getter
     * return result
     *
     * NOTE: record can be use as Utility class
     * record can be use as YML to object class @Config class in spring boot
     * record can be use to store db return data
     * record can be use to store service API response
     */
    public Integer add(Integer a, Integer b){
        return a + b + id();
    }
}

record Vehicle5(String name, Integer id){
    //field: instance filed (❌), static field (✅)
    //method: instance filed (✅), static field (✅)

//    String type;//instance filed
    static String engine;//static field

    public String instanceMethod(){
        return engine;
    }

    public static String staticMethod(){
        engine = "bmw";
        return engine;
    }
}

record Vehicle6(String name, Integer id){
    /**
     * compact constructor
     * @param name
     * @param id
     */
    public Vehicle6{
        if (id < 0) {
            System.err.println("id less than 0");
            throw new IllegalArgumentException("oops id less than 0");
        }
    }
}
//record by default final, like lombok utility class, we can't extend record,
//we can use nested record, record inside record (i.e. record as argument)

record Vehicle7(String name, Integer id, Vehicle6 vehicle6){
    public Vehicle7(String name, Integer id, Vehicle6 vehicle6) {//canonical constructor
        this.name = "prefix : " + name;
        this.id = id;
        this.vehicle6 = vehicle6;
    }
}

public class TestRecord {
    public static void main(String[] args) {

        VehicleDto vehicleDto = new VehicleDto("car", 123);
        System.out.println("vehicleDto.getName() = " + vehicleDto.getName());
        System.out.println("vehicleDto.getId() = " + vehicleDto.getId());
        System.out.println("vehicleDto.toString() = " + vehicleDto.toString());

        //record : pass all values to constructor
        Vehicle1 vehicle1 = new Vehicle1("bike", 234);
        System.out.println("\nvehicle1.name() = " + vehicle1.name());
        System.out.println("vehicle1.id() = " + vehicle1.id());
        System.out.println("vehicle1.toString() = " + vehicle1.toString());

        //record : we can pass null to constructor
        Vehicle1 vehicle1_2 = new Vehicle1(null, null);
        System.out.println("\nvehicle1_2.name() = " + vehicle1_2.name());
        System.out.println("vehicle1_2.id() = " + vehicle1_2.id());
        System.out.println("vehicle1_2.toString() = " + vehicle1_2.toString());

        //record : create constructor with less argument
        Vehicle2 vehicle2 = new Vehicle2("truck");
        System.out.println("\nvehicle2.name() = " + vehicle2.name());
        System.out.println("vehicle2.id() = " + vehicle2.id());
        System.out.println("vehicle2 = " + vehicle2);

        //record : instance method
        Vehicle3 vehicle3 = new Vehicle3("bus", 123);
        String toUpperCase = vehicle3.toUpperCase();
        System.out.println("\ntoUpperCase = " + toUpperCase);

        Vehicle4 vehicle4 = new Vehicle4("car", 100);
        Integer add = vehicle4.add(2, 4);
        System.out.println("\nadd = " + add);

        //instance & static
        Vehicle5 vehicle5 = new Vehicle5("bmw", 456);
        String instanceMethod = vehicle5.instanceMethod();
        System.out.println("\ninstanceMethod = " + instanceMethod);
        String staticMethod = Vehicle5.staticMethod();
        System.out.println("staticMethod = " + staticMethod);

        //compact constructor
//        Vehicle6 vehicle6 = new Vehicle6("bmw z1", -1);//error
        Vehicle6 vehicle6 = new Vehicle6("bmw z1", 123);

        //nested record
        Vehicle7 vehicle7 = new Vehicle7("bmw z2", 100, vehicle6);
        System.out.println("vehicle7 = " + vehicle7);

        //record : java reflection feature
        boolean record = vehicle7.getClass().isRecord();
        System.out.println("record = " + record);
        RecordComponent[] recordComponents = vehicle7.getClass().getRecordComponents();
        for (RecordComponent recordComponent : recordComponents){
            System.out.println(recordComponent);
        }

    }
}
