package pl.devdmin.core.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing an acquisition.
 *
 * @author Damian Ujma
 */
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

    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Acquisition that = (Acquisition) o;

        if (amount != that.amount) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (!id.equals(that.id)) return false;
        if (!product.equals(that.product)) return false;
        return buyDate.equals(that.buyDate);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + amount;
        result = 31 * result + product.hashCode();
        result = 31 * result + buyDate.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Acquisition{" +
                "id=" + id +
                ", amount=" + amount +
                ", product=" + product +
                ", buyDate=" + buyDate +
                ", price=" + price +
                '}';
    }
}
