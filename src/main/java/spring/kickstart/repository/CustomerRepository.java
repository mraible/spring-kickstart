package spring.kickstart.repository;

import spring.kickstart.domain.Customer;

import java.util.List;

/**
 * @author trisberg
 */
public interface CustomerRepository {

    Customer findById(Long id);

    Customer save(Customer customer);

    List<Customer> findAll();
}
