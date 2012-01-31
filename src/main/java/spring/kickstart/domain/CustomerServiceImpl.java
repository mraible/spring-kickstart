package spring.kickstart.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.kickstart.repository.CustomerRepository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.omg.CORBA.TRANSACTION_REQUIRED;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author trisberg
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final Log log = LogFactory.getLog(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    public Customer locateCustomerById(Long id) {
        if (log.isDebugEnabled())
            log.debug("executing 'locateCustomerById' method...: searching for ID: " + id);
        return customerRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewCustomer(Customer customer) {

        customerRepository.save(customer);

        if (log.isDebugEnabled() && customer != null)
            log.debug("executing 'addNewCustomer' method...: ID assigned: " + customer.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer updateCustomer(Customer customer) {
        if (log.isDebugEnabled() && customer != null)
            log.debug("executing 'updateCustomer' method...: updating ID: " + customer.getId());

        return customerRepository.save(customer);
    }

    public List<Customer> getListOfCustomers() {
        if (log.isDebugEnabled())
            log.debug("executing 'getListOfCustomers' method...");
        return customerRepository.findAll();
    }

}
