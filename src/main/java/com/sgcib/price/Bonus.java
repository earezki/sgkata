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
        int totalQuantity = this.buy + this.free;
        BigDecimal divide = quantity.divide(new BigDecimal(totalQuantity), 0, BigDecimal.ROUND_DOWN);
        BigDecimal remainder = quantity.remainder(new BigDecimal(totalQuantity));
        return new BigDecimal(this.buy).multiply(divide).add(remainder);
    }
}