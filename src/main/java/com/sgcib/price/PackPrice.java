package com.sgcib.price;

import com.sgcib.Quantity;

import java.math.BigDecimal;

class PackPrice implements Price {

    private final Price origin;
    private final BigDecimal packQuantity;

    PackPrice(Price origin, BigDecimal packQuantity) {
        this.origin = origin;
        this.packQuantity = packQuantity;
    }

    @Override
    public BigDecimal price(BigDecimal quantity) {
        return origin.price(quantity);
    }

}
