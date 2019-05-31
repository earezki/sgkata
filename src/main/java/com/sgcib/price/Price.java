package com.sgcib.price;

import java.math.BigDecimal;

@FunctionalInterface
public interface Price {

    BigDecimal price(BigDecimal quantity);

}
