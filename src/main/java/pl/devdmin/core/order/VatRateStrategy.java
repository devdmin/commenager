package pl.devdmin.core.order;

import java.math.BigDecimal;

public interface VatRateStrategy {
    BigDecimal calculatePriceWithVat(BigDecimal price);
}
