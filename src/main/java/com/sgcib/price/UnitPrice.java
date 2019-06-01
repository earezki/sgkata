package com.sgcib.price;

import java.math.BigDecimal;

class UnitPrice implements Price {

    private final BigDecimal value;

    UnitPrice(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal price(BigDecimal measure) {
        return this.value.multiply(measure);
    }
}
