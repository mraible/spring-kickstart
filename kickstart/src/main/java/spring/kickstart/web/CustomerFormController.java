package spring.kickstart.web;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.CancellableFormController;
import spring.kickstart.domain.Customer;
import spring.kickstart.domain.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mraible
 */
public class CustomerFormController extends CancellableFormController {
    private CustomerService customerService;

    public CustomerFormController(CustomerService customerService) {
        setCommandClass(Customer.class);
        setCommandName("customer");
        this.customerService = customerService;
    }

    @Override
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
        // convert java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,
                new CustomDateEditor(dateFormat, true));

        // convert java.lang.Long
        binder.registerCustomEditor(Long.class, null,
                new CustomNumberEditor(Long.class, null, true));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
            throws ServletException {
        String id = request.getParameter("id");

        if ((id != null) && !id.equals("")) {
            Customer customer = customerService.locateCustomerById(new Long(id));

            if (customer == null) {
                return new Customer();
            }

            return customer;
        } else {
            return new Customer();
        }
    }

    @Override
    protected void doSubmitAction(Object object) throws Exception {
        Customer customer = (Customer) object;
        if (customer.getId() == null) {
            customerService.addNewCustomer(customer);
        } else {
            customerService.updateCustomer(customer);
        }
    }
}
