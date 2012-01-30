package spring.kickstart.domain;

import junit.framework.TestCase;

import java.util.List;
import java.util.Date;

/**
 * @author mraible
 */
public class CustomerServiceMockTest extends TestCase {
    CustomerService customerService = new CustomerServiceMock();

    public void testGetById() throws Exception {
        Customer c = customerService.locateCustomerById(1L);
        assertEquals(new Long(1), c.getId());
        assertEquals("New Belgium Brewery", c.getName());
    }

    public void testGetAllCustomers() throws Exception {
        List customers = customerService.getListOfCustomers();
        assertEquals(3, customers.size());
    }

    public void testAddCustomer() throws Exception {
        Customer c = new Customer();
        c.setName("New Customer");
        c.setCustomerSince(new Date());
        customerService.addNewCustomer(c);
        assertNotNull(c.getId());
        System.out.println("--> assigned id: " + c.getId());
    }
}
