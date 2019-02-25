package pl.devdmin.core.order.vatStrategies;

import pl.devdmin.core.order.VatRateStrategy;

import java.math.BigDecimal;

public class Vat0 implements VatRateStrategy {
    @Override
    public BigDecimal calculatePriceWithVat(BigDecimal price) {
        return new BigDecimal(0);
    }
}
