package spring.kickstart.domain;

import java.util.*;

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

        // order item 1
        OrderItem item = new OrderItem();
        item.setId(1L);
        item.setLineNo(1);
        Product p = new Product();
        p.setId(1L);
        p.setDescription("Fat Tire");
        item.setProduct(p);
        orderItems.add(item);

        // order item 2
        OrderItem item2 = new OrderItem();
        item2.setId(1L);
        item2.setLineNo(1);
        Product p2 = new Product();
        p2.setId(2L);
        p2.setDescription("Sunshine Wheat");
        item2.setProduct(p2);
        orderItems.add(item2);

        Order order = new Order();
        order.setId(2L);
        order.setOrderDate(new Date());
        order.setOrderItems(orderItems);

        Collection<Order> orders = new ArrayList<Order>();
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

    public void addNewCustomer(Customer customer) {
        long primaryKey = repository.size() + 1;
        customer.setId(primaryKey);
        repository.put(customer.getId(), customer);
    }

    public Customer updateCustomer(Customer customer) {
        repository.put(customer.getId(), customer);
        return customer;
    }

    public List<Customer> getListOfCustomers() {
        return new ArrayList<Customer>(repository.values());
    }

}
