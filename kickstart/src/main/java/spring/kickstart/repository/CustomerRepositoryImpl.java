package spring.kickstart.repository;

import spring.kickstart.domain.Customer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author trisberg
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerRepositoryImpl implements CustomerRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }


    public Customer findById(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void add(Customer customer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Customer> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
