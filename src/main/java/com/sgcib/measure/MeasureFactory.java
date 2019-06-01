package com.sgcib.measure;

import java.math.BigDecimal;
import java.util.Optional;

public class MeasureFactory {

    static public class WeightParam {
        private final WeightUnit base;
        private final WeightUnit target;

        public WeightParam(WeightUnit base, WeightUnit target) {
            this.base = base;
            this.target = target;
        }

        Measure weight(double value) {
            return base.convertTo(value, target);
        }
    }

    public Measure create(double value, Optional<WeightParam> unit) {
        return unit.map(u -> weight(value, u))
                .orElseGet(() -> new Measure(new BigDecimal(value)));
    }

    private Measure weight(double value, WeightParam param) {
        return param.weight(value);
    }

}
