package spring.kickstart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.CustomerService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mraible
 */
@Controller
@RequestMapping("/customerform")
public class CustomerFormController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerValidator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // convert java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,
                new CustomDateEditor(dateFormat, true));

        // convert java.lang.Long
        binder.registerCustomEditor(Long.class, null,
                new CustomNumberEditor(Long.class, null, true));
    }

    @ModelAttribute("customer")
    @RequestMapping(method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam(required = false) String id) {
        Customer customer;

        if ((id != null) && !id.equals("")) {
            customer = customerService.locateCustomerById(new Long(id));
        } else {
            customer = new Customer();
        }

        return customer;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute Customer customer, BindingResult errors,
                         HttpServletRequest request) throws Exception {
        boolean doValidate = (request.getParameter("cancel") == null &&
                request.getParameter("delete") == null);

        if (doValidate) { // don't validate when deleting or cancelling
            validator.validate(customer, errors);
            if (errors.hasErrors()) {
                return "customerform";
            } else {
                if (customer.getId() == null) {
                    customerService.addNewCustomer(customer);
                } else {
                    customerService.updateCustomer(customer);
                }
            }
        }

        return "redirect:customers";
    }
}
