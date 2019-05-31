package com.sgcib.measure;

import com.google.common.collect.ImmutableMap;
import com.sgcib.price.Price;

import java.math.BigDecimal;
import java.util.Map;

public class Weight implements Measure {

    public enum Unit {
        POUND(ImmutableMap.of("OUNCE", 16.0, "POUND", 1.0)),
        OUNCE(ImmutableMap.of("OUNCE", 1.0, "POUND", 0.0625));

        private final Map<String, Double> table;

        Unit(Map<String, Double> table) {
            this.table = table;
        }

        public Weight convertTo(double value, Unit target) {
            Double rate = this.table.get(target.name());
            return new Weight(value * rate, target);
        }
    }

    private final double value;
    private final Unit unit;

    Weight(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public BigDecimal price(Price price) {
        return price.price(new BigDecimal(this.value));
    }

}
