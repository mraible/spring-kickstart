package spring.kickstart.domain;

import static javax.persistence.EnumType.STRING;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.GenerationType.*;
import java.util.List;
import java.util.Date;

/**
 * @author trisberg
 */
@Entity
@Table(name = "orders") // ORDER is a reserved word in most, if not all, SQL databases - used with ORDER BY
public class Order {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Temporal(DATE) private Date orderDate;

    @Enumerated(value = STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = ALL)
    @OrderBy("lineNo")
    private List<OrderItem> orderItems;

    @ManyToOne
    Customer customer;

    @Version
    private long version;

    public Order() {
        this.status = OrderStatus.PENDING;
    }

    public Order(Customer customer) {
        this.status = OrderStatus.PENDING;
        this.orderDate = new Date();
        this.customer = customer;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;    
    }

    public void ship() {
        this.status = OrderStatus.SHIPPED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
