package com.sgcib;

public class Weight {

    public enum Unit {
        POUND,
        OUNCE;
    }

    private final int value;

    public Weight(int value, Unit unit) {
        this.value = value;
    }



}
