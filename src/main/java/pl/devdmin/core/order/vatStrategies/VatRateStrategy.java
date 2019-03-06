package pl.devdmin.core.order.vatStrategies;

import java.math.BigDecimal;

public interface VatRateStrategy {
    BigDecimal calculatePriceWithVat(BigDecimal price);
    int getVat();
}
