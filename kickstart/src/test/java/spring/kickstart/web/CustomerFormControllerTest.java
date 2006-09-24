package spring.kickstart.web;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import junit.framework.TestCase;
import spring.kickstart.domain.CustomerServiceMock;
import spring.kickstart.domain.Customer;

/**
 * @author mraible
 */
public class CustomerFormControllerTest extends TestCase {
    private CustomerFormController form;

    public void setUp() throws Exception {
        form = new CustomerFormController(new CustomerServiceMock());
    }

    public void testEdit() throws Exception {
        // verify controller can grab user
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "");
        request.addParameter("id", "1");
        ModelAndView mv = form.handleRequest(request, new MockHttpServletResponse());
        //assertEquals("customerform", mv.getViewName());
        assertEquals("customer", form.getCommandName());
        Customer customer = (Customer) mv.getModel().get(form.getCommandName());
        assertEquals(new Long(1), customer.getId());
    }
}
