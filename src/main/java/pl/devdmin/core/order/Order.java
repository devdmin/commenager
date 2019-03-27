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

@Data
@Builder(builderMethodName = "defaultBuilder")
public class Order implements Model {
    private Long id;
    public static OrderBuilder builder() {
        return defaultBuilder().date(LocalDate.now());
    }

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
                .build();
    }
}
