package io.hari.machinecodingtips.interface_pattern;

public class TestClassA {
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassA();//this is simple class + hashmap data type present by m1. using extend hashmap (m2 we can use hashmap<> local variable)
        myClassA.someMethodThatUpdateTheMap();


        String hari = myClassA.getFromMap("hari");
        System.out.println("hari = " + hari);

        String first = myClassA.getFromMap("first");
        System.out.println("first = " + first);


        //set directly to map object, i.e. our class act like a map object
        myClassA.put("second", "value2");
        //get from map object
        String second = myClassA.get("second");
        System.out.println("second = " + second);

        //get from map via class own method : here we can add some validation / some procesing
        String second1 = myClassA.getFromMap("second");
        System.out.println("second1 = " + second1);


    }
}
