package spring.kickstart.domain;

import javax.persistence.*;
import static javax.persistence.GenerationType.*;
import java.util.List;

/**
 * @author trisberg
 */
@Entity
@Table(name = "orders") // ORDER is a reserved word in most, if not all, SQL databases - used with ORDER BY
public class Order {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @OneToMany(mappedBy = "order")
    @OrderBy("lineNo")
    private List<OrderItem> orderItems;

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
}
