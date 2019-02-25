package pl.devdmin.core.order;

import java.math.BigDecimal;

public interface ShippingCalculationStrategy {
    BigDecimal getShippingCost();
}
