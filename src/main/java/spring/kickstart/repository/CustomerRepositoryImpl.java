package spring.kickstart.repository;

import spring.kickstart.domain.Customer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author trisberg
 */
@Repository
@Transactional(readOnly = true)
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public Customer findById(Long id) {
        Customer c =  em.find(Customer.class,id);
        // to avoid lazy load issue - make sure all orders are loaded
        if (c != null && c.getOrders() != null && c.getOrders().size() > 0) {
            c.getOrders().iterator();
        }
        return c;
    }

    @Transactional
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
            return customer;
        } else {
            return em.merge(customer);
        }
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c")
                .getResultList();
    }
}
