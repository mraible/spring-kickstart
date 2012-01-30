package spring.kickstart.repository;

import spring.kickstart.domain.Customer;

import java.util.List;

/**
 * @author trisberg
 */
public interface CustomerRepository {

    Customer findById(Long id);

    void add(Customer customer);

    Customer merge(Customer customer);

    List<Customer> findAll();
}
