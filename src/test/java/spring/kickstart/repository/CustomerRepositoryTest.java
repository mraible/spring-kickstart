package spring.kickstart.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.Order;
import spring.kickstart.domain.Product;
import spring.kickstart.domain.OrderItem;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author trisberg
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repository-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CustomerRepositoryTest {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CustomerRepository customerRepository;

    @Before
    @Transactional
    public void onSetUpInTransaction() {

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
        List<OrderItem> ois = new ArrayList<OrderItem>();
        ois.add(oi1);
        o1.setOrderItems(ois);
        List<Order> os = new ArrayList<Order>();
        os.add(o1);
        c.setOrders(os);

        em.persist(c);
    }

    @Test
    public void testAddCustomer() {
        Customer c = new Customer();
        c.setName("New");
        c.setCustomerSince(new Date());
        customerRepository.save(c);
        List<Customer> customers = customerRepository.findAll();
        assertTrue(customers.contains(c));
    }

    @Test
    public void testRetrieveCustomer() {
        Customer c = new Customer();
        c.setName("NewCustomer!");
        c.setCustomerSince(new Date());
        customerRepository.save(c);
        Long newId = c.getId();
        c = null;
        Customer c2 = customerRepository.findById(newId);
        assertTrue("NewCustomer!".equals(c2.getName()));
    }

}
