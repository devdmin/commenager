package pl.devdmin.core.order.vatStrategies;

import pl.devdmin.core.order.VatRateStrategy;

import java.math.BigDecimal;

public class Vat8 implements VatRateStrategy {
    @Override
    public BigDecimal calculatePriceWithVat(BigDecimal price) {
        return price.multiply(new BigDecimal(("0.08")));
    }
}
