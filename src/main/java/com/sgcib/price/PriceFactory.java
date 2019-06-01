package com.sgcib.price;

import java.math.BigDecimal;
import java.util.Optional;

public class PriceFactory {

    public Price create(BigDecimal value, Optional<BigDecimal> packQuantity, Optional<Bonus> bonusParam) {
        return bonusParam.map(bonus -> bonus(value, bonus))
                .orElseGet(() -> packQuantity
                        .map(quantity -> pack(value, quantity))
                        .orElseGet(() -> new UnitPrice(value)));
    }

    private Price bonus(BigDecimal value, Bonus bonus) {
        return new BonusPrice(new UnitPrice(value), bonus);
    }

    private Price pack(BigDecimal value, BigDecimal packQuantity) {
        return new PackPrice(new UnitPrice(value), packQuantity);
    }

}
