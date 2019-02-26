package pl.devdmin.core.product;


import pl.devdmin.core.Model;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.core.order.Order;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object representing a product.
 *
 * @author Damian Ujma
 */
@Entity
public class Product implements Model {

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Acquisition> getAcquisitions() {
        if (this.acquisitions == null) {
            this.acquisitions = new HashSet<>();
        }
        return this.acquisitions;

    }

    public void setAcquisitions(Set<Acquisition> acquisitions) {
        this.acquisitions = acquisitions;
    }

    public Set<Order> getOrders() {
        if (this.orders == null) {
            this.orders = new HashSet<>();
        }
        return this.orders;
    }

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (acquisitions != null ? !acquisitions.equals(product.acquisitions) : product.acquisitions != null)
            return false;
        return orders != null ? orders.equals(product.orders) : product.orders == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (acquisitions != null ? acquisitions.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", acquisitions=" + acquisitions +
                ", orders=" + orders +
                '}';
    }
}
