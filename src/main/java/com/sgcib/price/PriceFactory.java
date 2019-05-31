package com.sgcib.price;

import java.math.BigDecimal;
import java.util.Optional;

public class PriceFactory {

    public Price create(BigDecimal value, Optional<BigDecimal> packQuantity) {
        return packQuantity
                .map(quantity -> pack(value, quantity))
                .orElseGet(() -> new UnitPrice(value));
    }

    private Price pack(BigDecimal value, BigDecimal packQuantity) {
        return new PackPrice(new UnitPrice(value), packQuantity);
    }

}
