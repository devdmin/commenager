package pl.devdmin.core.order.shippingStrategies;

import pl.devdmin.core.order.ShippingCalculationStrategy;
import pl.devdmin.core.order.ShippingMethod;

import java.math.BigDecimal;

public class PocztaPolskaShippingCalculationStrategy implements ShippingCalculationStrategy {
    @Override
    public BigDecimal getShippingCost() {
        return new BigDecimal("5.20");
    }

    @Override
    public String getShippingName() {
        return "Poczta Polska";
    }

    @Override
    public ShippingMethod getMethod() {
        return ShippingMethod.POCZTAPOLSKA;
    }
}
