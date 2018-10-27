package cz.muni.fi.pa165.entity;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class for halls' evidence
 * @author xtrnkal
 */
@Entity
public class Hall {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private Long capacity;

    private String address;

    public Hall(String name, Long capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Hall)) {
            return false;
        }
        Hall other = (Hall) obj;
        return Objects.equals(name, other.getName()) && Objects.equals(capacity, other.getCapacity());
    }

}
