package com.sgcib;

import java.math.BigDecimal;

public class Product {

    private final String name;
    private final Price price;
    private final Quantity quantity;

    public Product(String name, Price price, Quantity quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    BigDecimal price() {
        return quantity.price(new BigDecimal("0.6"));
    }
}
