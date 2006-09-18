package spring.kickstart.domain;

import java.util.List;

/**
 * @author trisberg
 */
public interface CustomerService {

    Customer locateCustomerById(Long id);

    public List<Customer> getListOfCustomers();
    
}
