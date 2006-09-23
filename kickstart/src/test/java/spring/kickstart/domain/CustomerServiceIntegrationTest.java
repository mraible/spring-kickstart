package spring.kickstart.domain;

import org.springframework.test.jpa.AbstractJpaTests;
import spring.kickstart.domain.*;
import spring.kickstart.repository.CustomerRepository;
import spring.kickstart.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author trisberg
 */
public class CustomerServiceIntegrationTest extends AbstractJpaTests {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private CustomerService customerService;
    private Long testId;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    protected String[] getConfigLocations() {
        return new String[] {"service-test-config.xml"};
    }

/*
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        setDefaultRollback(false);
    }
*/

    @Override
    protected void onSetUpInTransaction() throws Exception {

        Product p1 = new Product();
        p1.setDescription("Product1");
        productRepository.add(p1);

        Customer c = new Customer();
        c.setName("Test");
        c.setCustomerSince(new Date());
        Order o1 = new Order();
        o1.setOrderDate(new Date());
        o1.setCustomer(c);
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

        customerRepository.add(c);
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

}
