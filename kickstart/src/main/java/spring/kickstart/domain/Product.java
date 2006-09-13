package spring.kickstart.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author trisberg
 */
@Entity
public class Product {
    @Id private Long id;
    private String description;

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
