package com.sgcib;

import com.sgcib.measure.Measure;
import com.sgcib.measure.Quantity;
import com.sgcib.measure.Weight;
import com.sgcib.price.Price;
import com.sgcib.price.PriceFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CartBuilder {

    private List<Product> products = new ArrayList<>();

    class ProductBuilder {

        class PriceBuilder {

            private BigDecimal value;
            private BigDecimal quantity;
            private Weight.Unit unit;

            PriceBuilder withPrice(BigDecimal value) {
                this.value = value;
                return this;
            }

            PriceBuilder withQuantity(BigDecimal quantity) {
                this.quantity = quantity;
                return this;
            }

            ProductBuilder and() {
                PriceFactory priceFactory = new PriceFactory();
                return ProductBuilder.this.withPrice(priceFactory.create(value, Optional.ofNullable(quantity)));
            }

            Cart build() {
                return and().and().build();
            }

            PriceBuilder withWeight(Weight.Unit unit) {
                this.unit = unit;
                return null;
            }
        }

        private String name;
        private Price price;
        private Measure measure;

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
            this.measure = quantity;
            return this;
        }

        PriceBuilder withWeight(Weight weight) {
            this.measure = weight;
            return null;
        }

        CartBuilder and() {
            return CartBuilder.this.withProduct(new Product(name, price, measure));
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