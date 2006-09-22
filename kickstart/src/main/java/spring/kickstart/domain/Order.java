package spring.kickstart.domain;

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

    @OneToMany(mappedBy = "order", cascade = ALL)
    @OrderBy("lineNo")
    private List<OrderItem> orderItems;

    @Temporal(DATE) private Date orderDate;

    @ManyToOne
    Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
