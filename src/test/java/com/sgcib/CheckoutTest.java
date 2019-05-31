package com.sgcib;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {

    /**
     * This can of beans costs $0.65
     */
    @Test
    public void test_fixed_price() {
        Cart cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(new Quantity(1))
                .withPrice().withPrice(new BigDecimal("0.6")).withQuantity(new Quantity(1))
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("0.6"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(new Quantity(2))
                .withPrice().withPrice(new BigDecimal("0.6")).withQuantity(new Quantity(1))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.2"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(new Quantity(3))
                .withPrice().withPrice(new BigDecimal("0.7")).withQuantity(new Quantity(1))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("2.1"), totalPrice);
    }

    /**
     * Three for a dollar (so what’s the price if I buy 4, or 5?)
     */
    @Test
    public void test_variable_product_for_a_price() {
        Cart cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(new Quantity(1))
                .withPrice().withPrice(new BigDecimal("0.6")).withQuantity(new Quantity(3))
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.0"), totalPrice);
    }


}
