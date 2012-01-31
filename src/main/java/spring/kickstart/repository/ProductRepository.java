package spring.kickstart.repository;

import spring.kickstart.domain.Order;
import spring.kickstart.domain.Product;

import java.util.List;

/**
 * @author trisberg
 */
public interface ProductRepository {

    Product findById(Long id);

    Product save(Product p);

    List<Product> findAll();

}
