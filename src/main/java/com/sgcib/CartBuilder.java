package com.sgcib;

import com.sgcib.price.Price;
import com.sgcib.price.UnitPrice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class CartBuilder {

    private List<Product> products = new ArrayList<>();

    class ProductBuilder {

        class PriceBuilder {

            private BigDecimal value;
            private Quantity quantity;

            PriceBuilder withPrice(BigDecimal value) {
                this.value = value;
                return this;
            }

            PriceBuilder withQuantity(Quantity quantity) {
                this.quantity = quantity;
                return this;
            }

            ProductBuilder and() {
                return ProductBuilder.this.withPrice(new UnitPrice(value, quantity));
            }

            Cart build() {
                return and().and().build();
            }
        }

        private String name;
        private Price price;
        private Quantity quantity;

        ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        PriceBuilder withPrice() {
            return new PriceBuilder();
        }

        private ProductBuilder withPrice(Price price) {
            this.price = price;
            return this;
        }

        ProductBuilder withQuantity(Quantity quantity) {
            this.quantity = quantity;
            return this;
        }

        CartBuilder and() {
            return CartBuilder.this.withProduct(new Product(name, price, quantity));
        }
    }

    private CartBuilder withProduct(Product product) {
        products.add(product);
        return this;
    }

    ProductBuilder withProduct() {
        return new ProductBuilder();
    }

    Cart build() {
        return new Cart(products);
    }

}