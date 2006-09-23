package spring.kickstart.domain;

import spring.kickstart.repository.CustomerRepository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.omg.CORBA.TRANSACTION_REQUIRED;

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

    //@Transactional(propagation = Propagation.REQUIRED)
    public void addNewCustomer(Customer customer) {
        customerRepository.add(customer);
    }

    public List<Customer> getListOfCustomers() {
        return customerRepository.findAll();
    }

}
