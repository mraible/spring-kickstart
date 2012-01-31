package spring.kickstart.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.kickstart.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author trisberg
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }


    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Product save(Product p) {
        if (p.getId() == null) {
            em.persist(p);
            return p;
        } else {
            return em.merge(p);
        }
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p").getResultList();
    }
}
