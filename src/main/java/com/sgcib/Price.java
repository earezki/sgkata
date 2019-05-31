package com.sgcib;

import java.math.BigDecimal;

public class Price {

    private final BigDecimal value;

    public Price(BigDecimal value) {
        this.value = value;
    }

    BigDecimal price(BigDecimal quantity) {
        return this.value.multiply(quantity);
    }
}
