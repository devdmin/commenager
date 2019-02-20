package pl.devdmin.core.order;

import org.springframework.format.annotation.DateTimeFormat;
import pl.devdmin.core.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private ShippingCalculationStrategy shippingCalculationStrategy;

    public Order() {
        this.date = LocalDate.now();
    }

    @NotEmpty
    private int amount;

    @NotEmpty
    private String client;

    @NotEmpty
    private String address;

    @NotEmpty
    private double price;

    @NotEmpty
    private ShippingMethod shippingMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public ShippingCalculationStrategy getShippingCalculationStrategy() {
        return shippingCalculationStrategy;
    }

    public void setShippingCalculationStrategy(ShippingCalculationStrategy shippingCalculationStrategy) {
        this.shippingCalculationStrategy = shippingCalculationStrategy;
    }

    public double getShippingCost(){
        return shippingCalculationStrategy.getShippingCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (amount != order.amount) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (product != null ? !product.equals(order.product) : order.product != null) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        return address != null ? address.equals(order.address) : order.address == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", product=" + product +
                ", amount=" + amount +
                ", client='" + client + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                '}';
    }
}
