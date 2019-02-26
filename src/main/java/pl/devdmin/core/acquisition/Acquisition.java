package pl.devdmin.core.acquisition;

import org.springframework.format.annotation.DateTimeFormat;
import pl.devdmin.core.Model;
import pl.devdmin.core.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing an acquisition.
 *
 * @author Damian Ujma
 */
@Entity
public class Acquisition implements Model {
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

    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice(){
        return price.multiply(new BigDecimal(amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Acquisition that = (Acquisition) o;

        if (amount != that.amount) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (buyDate != null ? !buyDate.equals(that.buyDate) : that.buyDate != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + amount;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (buyDate != null ? buyDate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
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
