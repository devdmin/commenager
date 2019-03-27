package pl.devdmin.snapshot;

import lombok.Builder;
import lombok.Getter;
import pl.devdmin.core.acquisition.Acquisition;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class AcquisitionSnapshot {
    private int amount;

    private ProductSnapshot product;

    private LocalDate buyDate;

    private BigDecimal price;

    public Acquisition toDomain(){
        return Acquisition.builder()
                .amount(amount)
                .product(product.toDomain())
                .buyDate(buyDate)
                .price(price)
                .build();
    }
}
