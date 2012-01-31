package spring.kickstart.web;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.CustomerService;
import spring.kickstart.domain.CustomerServiceMock;

import java.util.List;

/**
 * @author mraible
 */
public class CustomerFormControllerTest extends AbstractDependencyInjectionSpringContextTests {
    private CustomerFormController form;

    public void setCustomerFormController(CustomerFormController form) {
        this.form = form;
    }

    protected String[] getConfigLocations() {
        return new String[]{"file:**/kickstart-servlet.xml", "repository-mock-config.xml"};
    }

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

    public void testAddNewCustomer() throws Exception {
        CustomerService service = (CustomerService) applicationContext.getBean("customerService");
        List customers = service.getListOfCustomers();
        
        Customer customer = new Customer();
        customer.setName("Chipotle");

        BindingResult errors = new DataBinder(customer).getBindingResult();
        form.submit(customer, errors, new MockHttpServletRequest());

        // assert no errors
        assertFalse(errors.hasErrors());

        assertEquals(service.getListOfCustomers().size() - 1, customers.size());
    }
}
