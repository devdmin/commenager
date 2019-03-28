package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;
import pl.devdmin.core.order.ShippingMethod;

import java.math.BigDecimal;

public class AllegroInpostShippingCalculationStrategy implements ShippingCalculationStrategy {

    public BigDecimal getShippingCost() {
        return new BigDecimal("8.99");
    }

    @Override
    public String getShippingName() {
        return "Allegro Inpost";
    }

    @Override
    public ShippingMethod getMethod() {
        return ShippingMethod.ALLEGROINPOST;
    }
}
