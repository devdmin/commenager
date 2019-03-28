package pl.devdmin.dao.product;

import lombok.Builder;
import lombok.Data;
import pl.devdmin.dao.acquisition.Acquisition;
import pl.devdmin.dao.order.Order;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple JavaBean domain object representing a product.
 *
 * @author Damian Ujma
 */
@Entity
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Acquisition> acquisitions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Order> orders;

    private int vatRate;


    public pl.devdmin.core.product.Product toDomain() {
        return pl.devdmin.core.product.Product.builder()
                .id(id)
                .name(name)
                .acquisitions(acquisitions.stream().map(acquisition -> acquisition.toDomain()).collect(Collectors.toSet()))
                .orders(orders.stream().map(order -> order.toDomain()).collect(Collectors.toSet()))
                .vatRate(vatRate)
                .build();
    }
}
