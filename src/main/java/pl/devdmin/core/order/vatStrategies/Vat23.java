package pl.devdmin.core.order.vatStrategies;

import java.math.BigDecimal;

public class Vat23 implements VatRateStrategy {
    @Override
    public BigDecimal calculatePriceWithVat(BigDecimal price) {
        return price.multiply(new BigDecimal(("0.23")));
    }

    @Override
    public int getVat() {
        return 23;
    }
}
