package pl.devdmin.core.order;

import java.math.BigDecimal;

public interface ShippingCalculationStrategy {
    BigDecimal getShippingCost();
    String getShippingName();
    ShippingMethod getMethod();
}
