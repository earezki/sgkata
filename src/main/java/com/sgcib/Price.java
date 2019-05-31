package com.sgcib;

import java.math.BigDecimal;

public class Price {

    private final BigDecimal value;

    public Price(BigDecimal value, Quantity quantity) {
        this.value = value;
    }

    BigDecimal price(BigDecimal quantity) {
        return this.value.multiply(quantity);
    }
}
