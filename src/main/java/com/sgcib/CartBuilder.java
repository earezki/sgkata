package com.sgcib;

import com.sgcib.measure.MeasureFactory;
import com.sgcib.measure.WeightUnit;
import com.sgcib.price.Bonus;
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
            private WeightUnit weightUnit;
            private Optional<Bonus> bonusParam = Optional.empty();

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
                return ProductBuilder.this.withPrice(priceFactory.create(value, Optional.ofNullable(quantity), bonusParam),
                        Optional.ofNullable(weightUnit));
            }

            Cart build() {
                return and().and().build();
            }

            PriceBuilder withWeight(WeightUnit weightUnit) {
                this.weightUnit = weightUnit;
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

                PriceBuilder and() {
                    return PriceBuilder.this.withBonus(buy, free);
                }

                Cart build() {
                    return and().build();
                }
            }

            private PriceBuilder withBonus(int buy, int free) {
                this.bonusParam = Optional.of(new Bonus(buy, free));
                return this;
            }

            BonusBuilder withBonus() {
                return new BonusBuilder();
            }
        }

        private String name;
        private Price price;
        private double measure;
        private WeightUnit weightUnit;
        private Optional<WeightUnit> priceUnit;

        ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        PriceBuilder withPrice() {
            return new PriceBuilder();
        }

        private ProductBuilder withPrice(Price price, Optional<WeightUnit> unit) {
            this.price = price;
            this.priceUnit = unit;
            return this;
        }

        ProductBuilder withQuantity(int quantity) {
            this.measure = quantity;
            return this;
        }

        ProductBuilder withWeight(double weight, WeightUnit weightUnit) {
            this.measure = weight;
            this.weightUnit = weightUnit;
            return this;
        }

        CartBuilder and() {
            MeasureFactory measureFactory = new MeasureFactory();
            Optional<MeasureFactory.WeightParam> unitParam = priceUnit.map(pu -> new MeasureFactory.WeightParam(this.weightUnit, pu));
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