package pl.devdmin.core.acquisition;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.devdmin.core.Model;
import pl.devdmin.core.product.Product;
import pl.devdmin.snapshot.AcquisitionSnapshot;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;



@Builder
public class Acquisition implements Model {
    private Long id;

    private int amount;

    private Product product;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPrice(){
        return price.multiply(new BigDecimal(amount));
    }

    public AcquisitionSnapshot toSnapshot(){
        return AcquisitionSnapshot.builder()
                .amount(amount)
                .product(product.toSnapshot())
                .buyDate(buyDate)
                .price(price)
                .totalPrice(getTotalPrice())
                .build();
    }
}
