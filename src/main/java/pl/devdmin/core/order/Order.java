package pl.devdmin.core.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devdmin.core.Model;
import pl.devdmin.core.order.vatStrategies.VatRateStrategy;
import pl.devdmin.core.product.Product;
import pl.devdmin.snapshot.OrderSnapshot;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
public class Order implements Model {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Product product;

    private ShippingCalculationStrategy shippingCalculationStrategy;


    private int amount;

    private String client;

    private String address;

    private BigDecimal price;

    private VatRateStrategy vatRateStrategy;

    public BigDecimal getShippingCost(){
        return shippingCalculationStrategy.getShippingCost();
    }
    public BigDecimal getVatValue(){
        return vatRateStrategy.calculatePriceWithVat(price.multiply(new BigDecimal(amount)));
    }
    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(amount)).add(getVatValue()).add(getShippingCost());
    }

    public OrderSnapshot toSnapshot(){
        return OrderSnapshot.builder()
                .product(product.toSnapshot())
                .amount(amount)
                .client(client)
                .address(address)
                .price(price)
                .date(date)
                .shippingMethod(shippingCalculationStrategy.getMethod())
                .vatValue(getVatValue())
                .shippingCost(getShippingCost())
                .totalPrice(getTotalPrice())
                .build();
    }

    public pl.devdmin.dao.order.Order toDao(){
        return pl.devdmin.dao.order.Order.builder()
                .id(id)
                .date(date)
                .product(product.toDao())
                .shippingCalculationStrategy(shippingCalculationStrategy)
                .amount(amount)
                .client(client)
                .address(address)
                .price(price)
                .vatRateStrategy(vatRateStrategy)
                .build();
    }
}
