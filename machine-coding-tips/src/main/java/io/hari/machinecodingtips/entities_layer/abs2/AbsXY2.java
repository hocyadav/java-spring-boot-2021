package io.hari.machinecodingtips.entities_layer.abs2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
abstract class AbsXY2 {
    public String absField;
    public String absField2 = "default abstract value : type";
    abstract void genericFun();
}
