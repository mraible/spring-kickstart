package spring.kickstart.web;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import junit.framework.TestCase;
import spring.kickstart.domain.CustomerServiceMock;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.CustomerService;

import java.util.Date;

/**
 * @author mraible
 */
public class CustomerFormControllerTest extends AbstractDependencyInjectionSpringContextTests {
    private CustomerFormController form;

    public void setCustomerFormController(CustomerFormController form) {
        this.form = form;
    }

    protected String[] getConfigLocations() {
        return new String[]{"file:**/kickstart-servlet.xml", "repository-config.xml"};
    }

    public void testEdit() throws Exception {
        form = new CustomerFormController(new CustomerServiceMock());
        // verify controller can grab user
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "");
        request.addParameter("id", "1");
        ModelAndView mv = form.handleRequest(request, new MockHttpServletResponse());
        //assertEquals("customerform", mv.getViewName());
        assertEquals("customer", form.getCommandName());
        Customer customer = (Customer) mv.getModel().get(form.getCommandName());
        assertEquals(new Long(1), customer.getId());
    }

    public void testAddNewCustomer() throws Exception {
        CustomerService service = (CustomerService) applicationContext.getBean("customerService");
        Customer customer = new Customer(null, "Chipotle", new Date());
        service.addNewCustomer(customer);
        assertNotNull(customer.getId());
    }
}
