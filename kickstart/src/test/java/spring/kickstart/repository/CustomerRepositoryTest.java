package spring.kickstart.repository;

import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.jdbc.core.JdbcTemplate;
import spring.kickstart.domain.Customer;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * @author trisberg
 */
public class CustomerRepositoryTest extends AbstractJpaTests {

    private CustomerRepository customerRepository;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected String[] getConfigLocations() {
        return new String[] {"repository-test-config.xml"};
    }

    protected void onSetUpInTransaction() throws Exception {

        Customer c = new Customer();
        c.setName("Test");
        c.setCustomerSince(new Date());
        customerRepository.add(c);

        super.onSetUpInTransaction();
    }

    public void testAddCustomer() {
        Customer c = new Customer();
        c.setName("New");
        c.setCustomerSince(new Date());
        customerRepository.add(c);
        List l = customerRepository.findAll();
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
