package io.hari.machinecodingtips.entities_layer.abstrct;

public class ClassX extends AbsXY {
    String localField;

    @Override
    public void calculate(){
        // do something
        System.out.println("localField = " + localField);
    }

    public void localFun(){
        //do something
        calculate();//can use generic fun: optional
    }
}
