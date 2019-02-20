package pl.devdmin.core.order.vatStrategies;

import pl.devdmin.core.order.VatRateStrategy;

public class Vat7 implements VatRateStrategy {
    @Override
    public double calculatePriceWithVat(double price) {
        return price * 0.07;
    }
}
