package pl.devdmin.dao.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devdmin.core.order.ShippingCalculationStrategy;
import pl.devdmin.core.order.ShippingMethod;
import pl.devdmin.core.order.vatStrategies.VatRateStrategy;
import pl.devdmin.dao.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing a order.
 *
 * @author Damian Ujma
 */
@Entity
@Table(name = "Orders")
@Data
@Builder
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Convert(converter = ShippingCalculationStrategyConverter.class)
    private ShippingCalculationStrategy shippingCalculationStrategy;

    private int amount;

    private String client;

    private String address;

    private BigDecimal price;

    @Convert(converter = VatRateStrategyConverter.class)
    private VatRateStrategy vatRateStrategy;

    public pl.devdmin.core.order.Order toDomain() {
        return pl.devdmin.core.order.Order.builder()
                .id(id)
                .date(date)
                .product(product.toDomain())
                .shippingCalculationStrategy(shippingCalculationStrategy)
                .amount(amount)
                .client(client)
                .address(address)
                .price(price)
                .vatRateStrategy(vatRateStrategy)
                .build();
    }
}
