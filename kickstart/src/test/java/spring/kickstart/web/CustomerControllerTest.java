package spring.kickstart.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.springframework.web.servlet.ModelAndView;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.CustomerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CustomerControllerTest extends MockObjectTestCase {
    final Log log = LogFactory.getLog(CustomerControllerTest.class);
    CustomerController c = new CustomerController();
    Mock mockService = null;

    protected void setUp() throws Exception {
        mockService = new Mock(CustomerService.class);
        c.setCustomerService((CustomerService) mockService.proxy());
    }

    public void testGetListOfCustomers() throws Exception {
        Customer cust1 = new Customer();
        cust1.setName("New Belgium Brewery");
        cust1.setCustomerSince(new Date());
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(cust1);
        
        // set expected behavior on service
        mockService.expects(once()).method("getListOfCustomers")
                   .will(returnValue(customers));

        ModelAndView mav = c.handleRequest(null, null);
        Map m = mav.getModel();
        assertNotNull(m.get("customerList"));
        log.debug(m.get("customerList"));
        //assertEquals("customers", mav.getViewName());

        // verify expectations
        mockService.verify();
    }
}
