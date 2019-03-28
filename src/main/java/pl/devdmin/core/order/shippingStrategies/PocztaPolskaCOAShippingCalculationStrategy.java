package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;
import pl.devdmin.core.order.ShippingMethod;

import java.math.BigDecimal;

public class PocztaPolskaCOAShippingCalculationStrategy implements ShippingCalculationStrategy {
    @Override
    public BigDecimal getShippingCost() {
        return new BigDecimal("16.80");
    }

    @Override
    public String getShippingName() {
        return "Poczta Polska Pobranie";
    }

    @Override
    public ShippingMethod getMethod() {
        return ShippingMethod.POCZTAPOLSKA_COA;
    }
}
