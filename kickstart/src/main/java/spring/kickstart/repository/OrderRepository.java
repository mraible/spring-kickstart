package spring.kickstart.repository;

import spring.kickstart.domain.Order;

import java.util.List;

/**
 * @author trisberg
 */
public interface OrderRepository {

    Order findById(Long id);

    List<Order> findAll();

}
