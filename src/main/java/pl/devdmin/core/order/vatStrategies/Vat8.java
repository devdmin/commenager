package pl.devdmin.core.order.vatStrategies;

import pl.devdmin.core.order.VatRateStrategy;

public class Vat8 implements VatRateStrategy {
    @Override
    public double calculatePriceWithVat(double price) {
        return price * 0.08;
    }
}