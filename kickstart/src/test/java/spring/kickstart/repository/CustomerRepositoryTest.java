package spring.kickstart.repository;

import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.Order;
import spring.kickstart.domain.Product;
import spring.kickstart.domain.OrderItem;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author trisberg
 */
public class CustomerRepositoryTest extends AbstractJpaTests {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private EntityManagerFactory emf;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    protected String[] getConfigLocations() {
        return new String[] {"repository-test-config.xml"};
    }

    protected void onSetUpInTransaction() throws Exception {

        EntityManager em =
                EntityManagerFactoryUtils.getTransactionalEntityManager(emf);

        Product p1 = new Product();
        p1.setDescription("Product1");
        em.persist(p1);

        Customer c = new Customer();
        c.setName("Test");
        c.setCustomerSince(new Date());
        Order o1 = new Order();
        OrderItem oi1 = new OrderItem();
        oi1.setLineNo(1);
        oi1.setOrder(o1);
        oi1.setProduct(p1);
        List ois = new ArrayList();
        ois.add(oi1);
        o1.setOrderItems(ois);
        List os = new ArrayList();
        os.add(o1);
        c.setOrders(os);

        em.persist(c);

        super.onSetUpInTransaction();
    }

    public void testAddCustomer() {
        Customer c = new Customer();
        c.setName("New");
        c.setCustomerSince(new Date());
        customerRepository.add(c);
        List<Customer> l = customerRepository.findAll();
        assertTrue(l.size() == 2);
    }

    public void testRetreiveCustomer() {
        Customer c = new Customer();
        c.setName("NewCustomer!");
        c.setCustomerSince(new Date());
        customerRepository.add(c);
        Long newId = c.getId();
        c = null;
        Customer c2 = customerRepository.findById(newId);
        assertTrue("NewCustomer!".equals(c2.getName()));
    }

}
