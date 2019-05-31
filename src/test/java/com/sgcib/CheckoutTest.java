package com.sgcib;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class CheckoutTest {

    /**
     * This can of beans costs $0.65
     */
    @Test
    public void test_fixed_price() {
        List<Product> products = asList(new Product("Can of beans", new Price(new BigDecimal("0.6")), new Quantity(1)));
        Cart cart = new Cart(products);
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("0.6"), totalPrice);

        products = asList(new Product("Can of beans", new Price(new BigDecimal("0.6")), new Quantity(2)));
        cart = new Cart(products);
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.2"), totalPrice);

        products = asList(new Product("Can of beans", new Price(new BigDecimal("0.7")), new Quantity(3)));
        cart = new Cart(products);
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("2.1"), totalPrice);
    }


}
