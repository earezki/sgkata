package com.sgcib;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class Cart {

    private final List<Product> products;

    public Cart(List<Product> products) {
        this.products = ImmutableList.copyOf(products);
    }

    public BigDecimal totalPrice() {
        return products.stream()
                .map(Product::price)
                .reduce(ZERO, BigDecimal::add);
    }
}
