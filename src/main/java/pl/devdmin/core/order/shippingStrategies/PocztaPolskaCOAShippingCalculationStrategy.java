package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;

public class PocztaPolskaCOAShippingCalculationStrategy implements ShippingCalculationStrategy {
    @Override
    public double getShippingCost() {
        return 16.80;
    }
}
