package pl.devdmin.core.order;

import org.springframework.format.annotation.DateTimeFormat;
import pl.devdmin.core.Model;
import pl.devdmin.core.order.vatStrategies.VatRateStrategy;
import pl.devdmin.core.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "Orders")
public class Order implements Model {
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

    public Order() {
        this.date = LocalDate.now();
    }

    public Order(Product product, ShippingCalculationStrategy shippingCalculationStrategy, @NotEmpty int amount, @NotEmpty String client, @NotEmpty String address, @NotEmpty BigDecimal price, VatRateStrategy vatRateStrategy) {
        this.date = LocalDate.now();
        this.product = product;
        this.shippingCalculationStrategy = shippingCalculationStrategy;
        this.amount = amount;
        this.client = client;
        this.address = address;
        this.price = price;
        this.vatRateStrategy = vatRateStrategy;
    }

    @NotEmpty
    private int amount;

    @NotEmpty
    private String client;

    @NotEmpty
    private String address;

    @NotEmpty
    private BigDecimal price;


    private ShippingMethod shippingMethod;

    @Convert(converter = VatRateStrategyConverter.class)
    private VatRateStrategy vatRateStrategy;




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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public VatRateStrategy getVatRateStrategy() {
        return vatRateStrategy;
    }

    public void setVatRateStrategy(VatRateStrategy vatRateStrategy) {
        this.vatRateStrategy = vatRateStrategy;
    }

    public void setShippingCalculationStrategy(ShippingCalculationStrategy shippingCalculationStrategy) {
        this.shippingCalculationStrategy = shippingCalculationStrategy;
    }

    public BigDecimal getShippingCost(){
        return shippingCalculationStrategy.getShippingCost();
    }

    public BigDecimal getVatValue(){
        return vatRateStrategy.calculatePriceWithVat(price.multiply(new BigDecimal(amount)));
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(amount)).add(getVatValue()).add(getShippingCost());
    }

    public String getShippingName(){
        return shippingCalculationStrategy.getShippingName();
    }
    public int getVat(){
        return vatRateStrategy.getVat();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (amount != order.amount) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (product != null ? !product.equals(order.product) : order.product != null) return false;
        if (shippingCalculationStrategy != null ? !shippingCalculationStrategy.equals(order.shippingCalculationStrategy) : order.shippingCalculationStrategy != null)
            return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (shippingMethod != order.shippingMethod) return false;
        return vatRateStrategy != null ? vatRateStrategy.equals(order.vatRateStrategy) : order.vatRateStrategy == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (shippingCalculationStrategy != null ? shippingCalculationStrategy.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (shippingMethod != null ? shippingMethod.hashCode() : 0);
        result = 31 * result + (vatRateStrategy != null ? vatRateStrategy.hashCode() : 0);
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
