package com.sgcib;

import com.sgcib.measure.Measure;
import com.sgcib.price.Price;

import java.math.BigDecimal;

public class Product {

    private final String name;
    private final Price price;
    private final Measure measure;

    public Product(String name, Price price, Measure measure) {
        this.name = name;
        this.price = price;
        this.measure = measure;
    }

    BigDecimal price() {
        return measure.price(price);
    }
}
