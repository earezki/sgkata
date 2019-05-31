package com.sgcib;

import java.math.BigDecimal;

public class Quantity {

    private final int value;

    public Quantity(int value) {
        this.value = value;
    }

    BigDecimal price(Price price) {
        return price.price(new BigDecimal(this.value));
    }
}
