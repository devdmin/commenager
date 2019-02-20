package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;

public class AllegroInpostShippingCalculationStrategy implements ShippingCalculationStrategy {

    public double getShippingCost() {
        return 8.99;
    }
}
