package pl.devdmin.dao.acquisition;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;


/**
 * Repository class for <code>Acquisition</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Damian Ujma
 */
public interface AcquisitionRepository extends Repository<Acquisition, Long> {
    /**
     * Save a <code>Acquisition</code> to the data store, either inserting or updating it.
     *
     * @param acquisition the <code>Acquisition</code> to save
     */
    void save(Acquisition acquisition) throws DataAccessException;
}
