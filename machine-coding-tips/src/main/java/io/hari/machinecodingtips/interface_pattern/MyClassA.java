package io.hari.machinecodingtips.interface_pattern;

import java.util.HashMap;

public class MyClassA extends HashMap<String, String> {
    //we can add HashMap<String, Collection<Object>> or any complex data type we can store in map key-value

    public void someMethodThatUpdateTheMap(){
        //some operation

        //call hasmap , we dont have hashmap object here but its a hashmap and we can directly call its method
        put("first", "value1");
    }

    public String getFromMap(String name){
    //other operation

        //call from map
        return get(name);
    }

}
