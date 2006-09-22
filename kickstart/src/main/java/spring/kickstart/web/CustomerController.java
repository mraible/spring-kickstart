package spring.kickstart.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import spring.kickstart.domain.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mraible
 * @created Sep 21, 2006 at 10:52:57 AM
 */
public class CustomerController implements Controller {
    private final Log log = LogFactory.getLog(CustomerController.class);
    private CustomerService customerService;

    public void setCustomerService(CustomerService userService) {
        this.customerService = userService;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        log.debug("entering 'handleRequest' method...");

        //return new ModelAndView("customers", "customerList", customerService.getListOfCustomers());
        return new ModelAndView().addObject(customerService.getListOfCustomers());
    }

}
