package com.sgcib;

import com.sgcib.measure.WeightUnit;
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
                .withProduct().withName("Can of beans").withQuantity(1)
                .withPrice().withPrice(new BigDecimal("0.6"))
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("0.6"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(2)
                .withPrice().withPrice(new BigDecimal("0.6"))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.2"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Can of beans").withQuantity(3)
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
                .withProduct().withName("Cookies").withQuantity(3)
                .withPrice().withPrice(new BigDecimal("1.0")).withQuantity(new BigDecimal(3))
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.0"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Cookies").withQuantity(6)
                .withPrice().withPrice(new BigDecimal("1.0")).withQuantity(new BigDecimal(3))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("2.0"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Cookies").withQuantity(4)
                .withPrice().withPrice(new BigDecimal("1.0")).withQuantity(new BigDecimal(3))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("2.0"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Cookies").withQuantity(5)
                .withPrice().withPrice(new BigDecimal("1.0")).withQuantity(new BigDecimal(3))
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("3.0"), totalPrice);
    }

    /**
     * $1.99/pound (so what does 4 ounces cost?)
     */
    @Test
    public void test_wighted_price() {
        Cart cart = new CartBuilder()
                .withProduct().withName("Rice").withWeight(1, WeightUnit.POUND)
                .withPrice().withPrice(new BigDecimal("1.99")).withWeight(WeightUnit.POUND)
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("1.99"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Rice").withWeight(4, WeightUnit.OUNCE)
                .withPrice().withPrice(new BigDecimal("1.99")).withWeight(WeightUnit.POUND)
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("0.4975"), totalPrice);
    }

    /**
     * buy two, get one free (so does the third item have a price?)
     */
    @Test
    public void test_bonus_products() {
        Cart cart = new CartBuilder()
                .withProduct().withName("Capsule").withQuantity(3)
                .withPrice().withPrice(new BigDecimal("1.0")).withBonus().buy(2).free(1)
                .build();
        BigDecimal totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("2.0"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Capsule").withQuantity(6)
                .withPrice().withPrice(new BigDecimal("1.0")).withBonus().buy(2).free(1)
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("4.0"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Capsule").withQuantity(7)
                .withPrice().withPrice(new BigDecimal("1.0")).withBonus().buy(2).free(1)
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("5.0"), totalPrice);

        cart = new CartBuilder()
                .withProduct().withName("Capsule").withQuantity(8)
                .withPrice().withPrice(new BigDecimal("1.0")).withBonus().buy(2).free(1)
                .build();
        totalPrice = cart.totalPrice();
        assertEquals(new BigDecimal("6.0"), totalPrice);
    }

}
