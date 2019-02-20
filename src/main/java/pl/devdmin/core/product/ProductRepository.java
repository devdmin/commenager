package pl.devdmin.core.product;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository class for <code>Product</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Damian Ujma
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * Retrieve an {@link Product} from the data store by name.
     * @param name the name to search for
     * @return the {@link Product} if found
     */
    Product findByName(String name);
}
