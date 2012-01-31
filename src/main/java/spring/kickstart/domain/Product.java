package spring.kickstart.domain;

import javax.persistence.*;
import static javax.persistence.GenerationType.*;

/**
 * @author trisberg
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private String description;

    @Version
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
