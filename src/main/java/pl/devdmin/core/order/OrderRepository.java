package pl.devdmin.core.order;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository class for <code>Order</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Damian Ujma
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

}
