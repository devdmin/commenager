package pl.devdmin.snapshot;

import lombok.Builder;
import lombok.Getter;
import pl.devdmin.core.product.Product;


@Getter
@Builder
public class ProductSnapshot {
    private String name;
    private int vatRate;

    public Product toDomain(){
        return Product.builder()
                .name(name)
                .vatRate(vatRate)
                .build();
    }



}
