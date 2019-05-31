package com.sgcib.measure;

import com.sgcib.price.Price;

import java.math.BigDecimal;

public interface Measure {

    BigDecimal price(Price price);

}
