package spring.kickstart.domain;

import junit.framework.TestCase;

import java.util.List;

/**
 * @author mraible
 * @created Sep 21, 2006 at 4:47:29 PM
 */
public class CustomerServiceMockTest extends TestCase {
    CustomerService customerService = new CustomerServiceMock();

    public void testGetById() throws Exception {
        Customer cust1 = customerService.locateCustomerById(1L);
        assertEquals(new Long(1), cust1.getId());
        assertEquals("New Belgium Brewery", cust1.getName());
    }

    public void testGetAllCustomers() throws Exception {
        List customers = customerService.getListOfCustomers();
        assertEquals(3, customers.size());
    }
}
