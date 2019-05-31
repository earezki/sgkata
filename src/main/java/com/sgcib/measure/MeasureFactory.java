package com.sgcib.measure;

import java.util.Optional;

public class MeasureFactory {

    static public class WeightParam {
        private final Weight.Unit base;
        private final Weight.Unit target;

        public WeightParam(Weight.Unit base, Weight.Unit target) {
            this.base = base;
            this.target = target;
        }

        Weight weight(double value) {
            return base.convertTo(value, target);
        }
    }

    public Measure create(double value, Optional<WeightParam> unit) {
        return unit.map(u -> weight(value, u))
                .orElseGet(() -> new Quantity((int) value));
    }

    private Measure weight(double value, WeightParam param) {
        return param.weight(value);
    }

}
