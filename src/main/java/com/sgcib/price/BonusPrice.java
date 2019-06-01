package com.sgcib.price;

import java.math.BigDecimal;

class BonusPrice implements Price {

    private final Price origin;
    private final Bonus bonus;

    BonusPrice(Price origin, Bonus bonus) {

        this.origin = origin;
        this.bonus = bonus;
    }

    @Override
    public BigDecimal price(BigDecimal quantity) {
        BigDecimal payableQuantity = bonus.payableQuantity(quantity);
        return origin.price(payableQuantity);
    }
}
