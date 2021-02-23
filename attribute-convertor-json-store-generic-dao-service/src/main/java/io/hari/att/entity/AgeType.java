package io.hari.att.entity;

public enum AgeType {
    GENERATION1(10),
    GENERATION2(20),
    GENERATION3(30);

    Integer value;
    AgeType(int i) {
        value = i;
    }

    public Integer getValue() {
        return value;
    }
}
