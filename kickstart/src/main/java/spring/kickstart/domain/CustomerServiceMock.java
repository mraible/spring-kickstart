package spring.kickstart.domain;

import oracle.toplink.essentials.sessions.Project;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * @author mraible
 */
public class CustomerServiceMock implements CustomerService {
    Map<Long, Customer> repository;

    public CustomerServiceMock() {
        repository = new LinkedHashMap<Long, Customer>();

        Customer c1 = new Customer(1L, "New Belgium Brewery", new Date());
        Customer c2 = new Customer(2L, "Breckenridge Brewery", new Date());
        Customer c3 = new Customer(3L, "Tommyknocker Brewery", new Date());

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        OrderItem item = new OrderItem();
        item.setId(1L);
        item.setLineNo(1);
        item.setOrder(new Order());
        Product p = new Product();
        p.setId(1L);
        p.setDescription("Fat Tire");
        item.setProduct(p);
        orderItems.add(item);

        Order order = new Order();
        order.setId(2L);
        order.setOrderItems(orderItems);

        Set<Order> orders = new LinkedHashSet<Order>();
        orders.add(order);

        c1.setOrders(orders);
        c2.setOrders(orders);
        c3.setOrders(orders);

        repository.put(c1.getId(), c1);
        repository.put(c2.getId(), c2);
        repository.put(c3.getId(), c3);
    }

    public Customer locateCustomerById(Long id) {
        return repository.get(id);
    }

    public List<Customer> getListOfCustomers() {
        return new ArrayList<Customer>(repository.values());
    }

}
