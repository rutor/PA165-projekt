package cz.muni.fi.pa165.entity;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Role class
 * @author Robert Dudas
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    private String description;

    public Role(){};

    public Role(Long id) {
        this();

        setId(id);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Role) {
            Role other = (Role) obj;
            if (!Objects.equals(description, other.getDescription())) {
                return false;
            }
            if (!Objects.equals(name, other.getName())) {
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}
