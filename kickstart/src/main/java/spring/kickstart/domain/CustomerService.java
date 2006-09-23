package spring.kickstart.domain;

import java.util.List;

/**
 * @author trisberg
 */
public interface CustomerService {

    Customer locateCustomerById(Long id);

    void addNewCustomer(Customer customer);

    public List<Customer> getListOfCustomers();
    
}
