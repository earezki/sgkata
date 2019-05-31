package com.sgcib.price;

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
        BigDecimal divide = quantity.divide(packQuantity, 0, BigDecimal.ROUND_DOWN);
        BigDecimal remainder = quantity.remainder(packQuantity);
        return origin.price(quantity(divide, remainder));
    }

    private BigDecimal quantity(BigDecimal divide, BigDecimal remainder) {
        return divide.add(remainder);
    }

}
