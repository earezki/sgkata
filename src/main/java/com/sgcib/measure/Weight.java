package com.sgcib.measure;

import com.sgcib.price.Price;

import java.math.BigDecimal;

public class Weight implements Measure {

    public enum Unit {
        POUND,
        OUNCE;
    }

    private final int value;
    private final Unit unit;

    Weight(int value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public BigDecimal price(Price price) {
        return price.price(new BigDecimal(this.value));
    }

}
