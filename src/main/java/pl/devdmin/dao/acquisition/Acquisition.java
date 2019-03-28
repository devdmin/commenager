package pl.devdmin.dao.acquisition;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devdmin.dao.product.Product;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing an acquisition.
 *
 * @author Damian Ujma
 */
@Entity
@Data
public class Acquisition {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private int amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotEmpty
    @Column(name = "buy_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    private BigDecimal price;

    public pl.devdmin.core.acquisition.Acquisition toDomain() {
        return pl.devdmin.core.acquisition.Acquisition.builder()
                .id(id)
                .amount(amount)
                .product(product.toDomain())
                .buyDate(buyDate)
                .price(price)
                .build();
    }
}
