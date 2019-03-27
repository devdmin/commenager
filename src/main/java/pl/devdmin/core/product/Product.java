package pl.devdmin.core.product;


import lombok.Builder;
import lombok.Data;
import pl.devdmin.core.Model;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.core.order.Order;
import pl.devdmin.snapshot.ProductSnapshot;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Builder
public class Product implements Model {
    private Long id;
    private String name;
    private Set<Acquisition> acquisitions;
    private Set<Order> orders;
    private int vatRate;

    public ProductSnapshot toSnapshot(){
        return ProductSnapshot.builder()
                .name(name)
                .vatRate(vatRate)
                .build();
    }
}
