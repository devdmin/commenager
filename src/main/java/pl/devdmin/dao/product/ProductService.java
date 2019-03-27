package pl.devdmin.dao.product;
import java.util.Set;

public interface ProductService {
    void save(Product product);
    Set<Product> findAll();
}
