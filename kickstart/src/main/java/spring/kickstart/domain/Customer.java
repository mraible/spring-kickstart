package spring.kickstart.domain;

import javax.persistence.*;
import static javax.persistence.TemporalType.*;
import static javax.persistence.GenerationType.*;
import java.util.Date;
import java.util.Set;

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
    @OneToMany Set<Order> orders;

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

}
