package com.sgcib.measure;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Map;

public enum WeightUnit {
    POUND(ImmutableMap.of("OUNCE", 16.0, "POUND", 1.0)),
    OUNCE(ImmutableMap.of("OUNCE", 1.0, "POUND", 0.0625));

    private final Map<String, Double> table;

    WeightUnit(Map<String, Double> table) {
        this.table = table;
    }

    Measure convertTo(double value, WeightUnit target) {
        Double rate = this.table.get(target.name());
        return new Measure(new BigDecimal(value * rate));
    }
}
