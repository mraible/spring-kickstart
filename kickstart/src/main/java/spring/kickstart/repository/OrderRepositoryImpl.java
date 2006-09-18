package spring.kickstart.repository;

import spring.kickstart.domain.Order;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author trisberg
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderRepositoryImpl implements OrderRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }


    public Order findById(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Order> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
