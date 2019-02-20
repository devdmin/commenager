package pl.devdmin.core.order;

public interface VatRateStrategy {
    double calculatePriceWithVat(double price);
}
