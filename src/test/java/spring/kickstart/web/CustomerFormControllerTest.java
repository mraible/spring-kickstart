package spring.kickstart.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.CustomerService;
import spring.kickstart.domain.CustomerServiceMock;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author mraible
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repository-config.xml"})
public class CustomerFormControllerTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerFormController form;

    @Test
    public void testEdit() throws Exception {
        form = new CustomerFormController();
        form.customerService = new CustomerServiceMock();
        // verify controller can grab user
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "");
        request.addParameter("id", "1");
        Customer customer = form.getCustomer("1");
        assertNotNull(customer);
        assertEquals(new Long(1), customer.getId());
    }

    @Test
    public void testAddNewCustomer() throws Exception {
        List customers = customerService.getListOfCustomers();

        Customer customer = new Customer();
        customer.setName("Chipotle");

        BindingResult errors = new DataBinder(customer).getBindingResult();
        form.submit(customer, errors, new MockHttpServletRequest());

        // assert no errors
        assertFalse(errors.hasErrors());

        assertEquals(customerService.getListOfCustomers().size() - 1, customers.size());
    }
}
