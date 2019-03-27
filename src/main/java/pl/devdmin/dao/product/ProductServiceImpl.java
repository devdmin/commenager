package pl.devdmin.dao.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public Set<Product> findAll() {
         return new HashSet<Product>((List<Product>) repository.findAll());
    }
}
