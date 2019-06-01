package com.sgcib;

import com.sgcib.measure.MeasureFactory;
import com.sgcib.measure.Weight;
import com.sgcib.price.Price;
import com.sgcib.price.PriceFactory;

import java.math.BigDecimal;
import java.sql.Array;
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
                return ProductBuilder.this.withPrice(priceFactory.create(value, Optional.ofNullable(quantity)), Optional.ofNullable(unit));
            }

            Cart build() {
                return and().and().build();
            }

            PriceBuilder withWeight(Weight.Unit unit) {
                this.unit = unit;
                return this;
            }

            class BonusBuilder {

                private int buy;
                private int free;

                BonusBuilder buy(int buy) {
                    this.buy = buy;
                    return this;
                }

                BonusBuilder free(int free) {
                    this.free = free;
                    return this;
                }

                Cart build() {
                    return PriceBuilder.this.build();
                }
            }

            BonusBuilder withBonus() {
                return new BonusBuilder();
            }
        }

        private String name;
        private Price price;
        private double measure;
        private Weight.Unit unit;
        private Optional<Weight.Unit> priceUnit;

        ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        PriceBuilder withPrice() {
            return new PriceBuilder();
        }

        private ProductBuilder withPrice(Price price, Optional<Weight.Unit> unit) {
            this.price = price;
            this.priceUnit = unit;
            return this;
        }

        ProductBuilder withQuantity(int quantity) {
            this.measure = quantity;
            return this;
        }

        ProductBuilder withWeight(double weight, Weight.Unit unit) {
            this.measure = weight;
            this.unit = unit;
            return this;
        }

        CartBuilder and() {
            MeasureFactory measureFactory = new MeasureFactory();
            Optional<MeasureFactory.WeightParam> unitParam = priceUnit.map(pu -> new MeasureFactory.WeightParam(this.unit, pu));
            return CartBuilder.this.withProduct(new Product(name, price, measureFactory.create(measure, unitParam)));
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