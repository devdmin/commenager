package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;

public class PocztaPolskaShippingCalculationStrategy implements ShippingCalculationStrategy {
    @Override
    public double getShippingCost() {
        return 5.20;
    }
}
