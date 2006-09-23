package spring.kickstart.domain;

import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import spring.kickstart.domain.*;
import spring.kickstart.repository.CustomerRepository;
import spring.kickstart.repository.ProductRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author trisberg
 */
public class CustomerServiceIntegrationTest extends AbstractJpaTests {

    private EntityManagerFactory emf;
    private CustomerService customerService;
    private Long testId;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    protected String[] getConfigLocations() {
        return new String[] {"service-test-config.xml"};
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {

        EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager(emf);

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

        super.onSetUpInTransaction();
    }

    public void testGetCustomerList() {
        List<Customer> l = customerService.getListOfCustomers();
        assertTrue(l.size() == 1);
    }

    public void testAddAndRetreiveCustomer() {

        Customer c = new Customer();
        c.setName("New2");
        c.setCustomerSince(new Date());
        customerService.addNewCustomer(c);

        Long newId = c.getId();
        c = null;

        Customer c2 = customerService.locateCustomerById(newId);
        assertTrue("New2".equals(c2.getName()));
    }

    public void testRetreiveExistingCustomer() {

        Customer c = customerService.locateCustomerById(testId);
        assertTrue("Test".equals(c.getName()));
        assertTrue(c.getOrders().size() > 0);
    }

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
