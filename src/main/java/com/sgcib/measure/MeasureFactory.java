package com.sgcib.measure;

import java.util.Optional;

public class MeasureFactory {

    public Measure create(int value, Optional<Weight.Unit> unit) {
        return unit.map(u -> weight(value, u))
                .orElseGet(() -> new Quantity(value));
    }

    private Measure weight(int value, Weight.Unit unit) {
        return new Weight(value, unit);
    }

}
