package spring.kickstart.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
public class CustomerServiceIntegrationTest {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CustomerService customerService;
    private Long testId;

    @Before
    @Transactional
    public void onSetUpInTransaction() throws Exception {

        Product p1 = new Product();
        p1.setDescription("Product1");
        em.persist(p1);

        Customer c = new Customer();
        c.setName("Test");
        c.setCustomerSince(new Date());
        Order o1 = new Order(c);
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
        testId = c.getId();
    }

    @Test
    public void testGetCustomerList() {
        List<Customer> l = customerService.getListOfCustomers();
        assertTrue(l.size() == 1);
    }

    @Test
    public void testAddAndRetrieveCustomer() {
        Customer c = new Customer();
        c.setName("New2");
        c.setCustomerSince(new Date());
        customerService.addNewCustomer(c);

        Long newId = c.getId();
        c = null;

        Customer c2 = customerService.locateCustomerById(newId);
        assertTrue("New2".equals(c2.getName()));
    }

    @Test
    public void testRetrieveExistingCustomer() {
        Customer c = customerService.locateCustomerById(testId);
        assertTrue("Test".equals(c.getName()));
        assertTrue(c.getOrders().size() > 0);
    }

    @Test
    public void testModifyExistingCustomer() {
        Customer c = customerService.locateCustomerById(testId);
        assertTrue("Test".equals(c.getName()));
        assertTrue(c.getOrders().size() > 0);
        Order o = (Order) c.getOrders().toArray()[0];
        assertEquals(OrderStatus.PENDING, o.getStatus());

        o.cancel();
        c = customerService.updateCustomer(c);
        c = null;

        Customer c2 = customerService.locateCustomerById(testId);
        assertTrue("Test".equals(c2.getName()));
        assertTrue(c2.getOrders().size() > 0);
        Order o2 = (Order) c2.getOrders().toArray()[0];
        assertEquals(OrderStatus.CANCELLED, o2.getStatus());
    }
}
