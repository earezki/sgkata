package com.sgcib.measure;

import com.sgcib.price.Price;

import java.math.BigDecimal;

public class Quantity implements Measure {

    private final int value;

    public Quantity(int value) {
        this.value = value;
    }

    @Override
    public BigDecimal price(Price price) {
        return price.price(new BigDecimal(this.value));
    }
}