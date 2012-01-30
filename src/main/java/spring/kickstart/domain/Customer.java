package spring.kickstart.domain;

import static javax.persistence.CascadeType.ALL;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.DATE;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author trisberg
 */

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    @Temporal(DATE) private Date customerSince;

    @OneToMany(mappedBy = "customer", cascade = ALL)
    Collection<Order> orders;

    @Version
    private long version;

    public Customer() {}

    public Customer(Long id, String name, Date customerSince) {
        this.id = id;
        this.name = name;
        this.customerSince = customerSince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(Date customerSince) {
        this.customerSince = customerSince;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return Customer.class.getSimpleName() + new LinkedHashMap<String, Object>() {
            {
                put("id", id);
                put("name", name);
                put("customerSince", customerSince);
            }
        };
    }
}
