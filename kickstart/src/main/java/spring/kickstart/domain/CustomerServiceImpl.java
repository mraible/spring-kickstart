package spring.kickstart.domain;

import spring.kickstart.repository.CustomerRepository;

import java.util.List;

/**
 * @author trisberg
 */
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer locateCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getListOfCustomers() {
        return customerRepository.findAll();
    }

}
