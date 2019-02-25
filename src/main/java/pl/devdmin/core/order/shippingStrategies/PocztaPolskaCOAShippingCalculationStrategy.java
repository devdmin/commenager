package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;

import java.math.BigDecimal;

public class PocztaPolskaCOAShippingCalculationStrategy implements ShippingCalculationStrategy {
    @Override
    public BigDecimal getShippingCost() {
        return new BigDecimal("16.80");
    }
}
