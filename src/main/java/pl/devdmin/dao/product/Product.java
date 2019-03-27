package pl.devdmin.dao.product;

import lombok.Data;
import pl.devdmin.dao.acquisition.Acquisition;
import pl.devdmin.dao.order.Order;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Simple JavaBean domain object representing a product.
 *
 * @author Damian Ujma
 */
@Entity
@Data
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
}
