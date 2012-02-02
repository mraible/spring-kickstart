package spring.kickstart.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import spring.kickstart.domain.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mraible
 */
@Controller
public class CustomerController {
    private final Log log = LogFactory.getLog(CustomerController.class);
    @Autowired
    CustomerService customerService;

    @RequestMapping("/customers")
    public String execute(ModelMap model)
    throws Exception {
        log.debug("entering 'execute' method...");
        model.addAttribute(customerService.getListOfCustomers());
        return "customers";
    }
}
