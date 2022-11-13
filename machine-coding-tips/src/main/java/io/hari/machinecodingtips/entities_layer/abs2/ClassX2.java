package io.hari.machinecodingtips.entities_layer.abs2;

import org.checkerframework.checker.units.qual.C;

public class ClassX2 extends AbsXY2 {
    String localField;

    public ClassX2() {
        setAbsField2("typ1");//set abs fild to identify type of object at run time
    }

    @Override
    public void genericFun(){
        // do something
        System.out.println("localField = " + localField);
        localFun();
    }

    public void localFun(){
        //do something

        //can use generic fun: optional
        genericFun();
        //can use generic field : optional
        setAbsField("set value from concrete class");
        String absField = getAbsField();

        String absField2 = getAbsField2();
        System.out.println("absField2 = " + absField2);
    }
    //generic func can use/call local fun() + local fields
    //local func can use/call generic fun() + generic fields
}
