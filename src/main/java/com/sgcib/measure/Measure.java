package com.sgcib.measure;

import com.sgcib.price.Price;

import java.math.BigDecimal;

public class Measure {

    private final BigDecimal value;

    public Measure(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal price(Price price) {
        return price.price(value);
    }

}
