package pl.devdmin.snapshot;

import lombok.Builder;
import lombok.Getter;
import pl.devdmin.core.order.Order;
import pl.devdmin.core.order.ShippingCalculationStrategy;
import pl.devdmin.core.order.ShippingMethod;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.InpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaCOAShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaShippingCalculationStrategy;
import pl.devdmin.core.order.vatStrategies.*;
import pl.devdmin.core.product.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class OrderSnapshot {
    private ProductSnapshot product;
    private int amount;

    private String client;

    private String address;

    private BigDecimal price;

    private ShippingMethod shippingMethod;

    private LocalDate date;

    private BigDecimal shippingCost;

    private BigDecimal vatValue;

    private BigDecimal totalPrice;

    public Order toDomain(){
        return Order.builder()
                .product(product.toDomain())
                .amount(amount)
                .client(client)
                .price(price)
                .shippingCalculationStrategy(chooseShippingStrategy(this))
                .vatRateStrategy(chooseVatRateStrategy(this))
                .build();
    }

    private ShippingCalculationStrategy chooseShippingStrategy(OrderSnapshot order){
        ShippingCalculationStrategy shippingCalculationStrategy;
        switch (order.getShippingMethod()) {
            case INPOST:
                shippingCalculationStrategy = new InpostShippingCalculationStrategy();
                break;
            case ALLEGROINPOST:
                shippingCalculationStrategy = new AllegroInpostShippingCalculationStrategy();
                break;
            case POCZTAPOLSKA_COA:
                shippingCalculationStrategy = new PocztaPolskaCOAShippingCalculationStrategy();
                break;
            case POCZTAPOLSKA:
                shippingCalculationStrategy = new PocztaPolskaShippingCalculationStrategy();
                break;
            default:
                throw new RuntimeException();
        }
        return shippingCalculationStrategy;
    }

    private VatRateStrategy chooseVatRateStrategy(OrderSnapshot order){
        VatRateStrategy vatRateStrategy;
        switch (order.getProduct().getVatRate()){
            case 0:
                vatRateStrategy = new Vat0();
                break;
            case 4:
                vatRateStrategy = new Vat4();
                break;
            case 7:
                vatRateStrategy = new Vat7();
                break;
            case 8:
                vatRateStrategy = new Vat8();
                break;
            case 23:
                vatRateStrategy = new Vat23();
                break;
            default:
                throw new RuntimeException();
        }
        return vatRateStrategy;
    }
}
