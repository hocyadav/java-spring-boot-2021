package io.hari.machinecodingtips.entities_layer.abstrct;

public class TestAbs {
    public static void main(String[] args) {
        takeGeneric(new ClassX());
    }

    public static void takeGeneric(AbsXY absXY){
        absXY.calculate();
    }
}
