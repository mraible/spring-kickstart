package spring.kickstart.web;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.validation.BindException;
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
        return new String[]{"file:**/kickstart-servlet.xml", "repository-config.xml"};
    }

    public void testEdit() throws Exception {
        form = new CustomerFormController(new CustomerServiceMock());
        // verify controller can grab user
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "");
        request.addParameter("id", "1");
        ModelAndView mv = form.handleRequest(request, new MockHttpServletResponse());
        assertEquals("customer", form.getCommandName());
        Customer customer = (Customer) mv.getModel().get(form.getCommandName());
        assertEquals(new Long(1), customer.getId());
    }

    public void testAddNewCustomer() throws Exception {
        CustomerService service = (CustomerService) applicationContext.getBean("customerService");
        List customers = service.getListOfCustomers();

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
        request.addParameter("id", "");
        request.addParameter("name", "Chipotle");
        request.addParameter("customerSince", "09/15/2006");

        ModelAndView mv = form.handleRequest(request, new MockHttpServletResponse());
        assertEquals("redirect:customers.htm", form.getSuccessView());

        // assert no errors
        Errors errors = (Errors) mv.getModel().get(BindException.MODEL_KEY_PREFIX + form.getCommandName());
        assertTrue(errors.getAllErrors().size() == 0);

        assertEquals(service.getListOfCustomers().size() - 1, customers.size());
    }
}
