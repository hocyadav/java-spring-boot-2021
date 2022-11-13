package io.hari.machinecodingtips.entities_layer.abstrct;

public class ClassY extends AbsXY{
    Integer localField;

    @Override
    public void calculate(){
        //do something
        System.out.println("localField = " + localField);
    }
}
