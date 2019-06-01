package com.sgcib.price;

import java.math.BigDecimal;

public class Bonus {

    private final int buy;
    private final int free;

    public Bonus(int buy, int free) {
        this.buy = buy;
        this.free = free;
    }

    BigDecimal payableQuantity(BigDecimal quantity) {
        BigDecimal totalQuantity = totalQuantity();
        BigDecimal divide = quantity.divide(totalQuantity, 0, BigDecimal.ROUND_DOWN);
        BigDecimal remainder = quantity.remainder(totalQuantity);
        return new BigDecimal(this.buy).multiply(divide).add(remainder);
    }

    private BigDecimal totalQuantity() {
        return new BigDecimal(this.buy + this.free);
    }
}