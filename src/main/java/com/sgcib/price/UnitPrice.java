package com.sgcib.price;

import com.sgcib.Quantity;

import java.math.BigDecimal;

public class UnitPrice implements Price {

    private final BigDecimal value;

    public UnitPrice(BigDecimal value, Quantity quantity) {
        this.value = value;
    }

    @Override
    public BigDecimal price(BigDecimal quantity) {
        return this.value.multiply(quantity);
    }
}
