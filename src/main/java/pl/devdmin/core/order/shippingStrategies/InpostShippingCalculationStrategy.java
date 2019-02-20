package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;

public class InpostShippingCalculationStrategy implements ShippingCalculationStrategy {
    @Override
    public double getShippingCost() {
        return 13.76;
    }
}
