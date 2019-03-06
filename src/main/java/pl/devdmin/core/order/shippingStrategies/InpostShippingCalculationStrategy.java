package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;

import java.math.BigDecimal;

public class InpostShippingCalculationStrategy implements ShippingCalculationStrategy {
    @Override
    public BigDecimal getShippingCost() {
        return new BigDecimal("13.76");
    }

    @Override
    public String getShippingName() {
        return "Inpost";
    }
}
