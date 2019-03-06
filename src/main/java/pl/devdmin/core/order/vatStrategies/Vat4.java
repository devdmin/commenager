package pl.devdmin.core.order.vatStrategies;

import java.math.BigDecimal;

public class Vat4 implements VatRateStrategy {
    @Override
    public BigDecimal calculatePriceWithVat(BigDecimal price) {
        return price.multiply(new BigDecimal(("0.04")));
    }

    @Override
    public int getVat() {
        return 4;
    }
}
