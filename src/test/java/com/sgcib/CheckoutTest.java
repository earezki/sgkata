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
                .withPrice().withPrice(new BigDecimal("0.6"))
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("0.6"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(new Quantity(2))
                .withPrice().withPrice(new BigDecimal("0.6"))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.2"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(new Quantity(3))
                .withPrice().withPrice(new BigDecimal("0.7"))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("2.1"), totalPrice);
    }

    /**
     * Three for a dollar (so what’s the price if I buy 4, or 5?)
     */
    @Test
    public void test_pack_products_for_a_price() {
        Cart cart = new CartBuilder()
                .withProduct().withName("Cookies").withQuantity(new Quantity(3))
                .withPrice().withPrice(new BigDecimal("1.0")).withQuantity(new BigDecimal(3))
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.0"), totalPrice);
    }


}
