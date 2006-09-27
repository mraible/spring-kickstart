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
        Customer c =  em.find(Customer.class,id);
        // to avoid lazy load issue - make sure all orders are loaded
        if (c != null && c.getOrders() != null && c.getOrders().size() > 0) {
            c.getOrders().iterator();
        }
        return c;
    }

    public void add(Customer customer) {
        em.persist(customer);
    }

    public Customer merge(Customer customer) {
        return em.merge(customer);
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c")
                .getResultList();
    }
}
