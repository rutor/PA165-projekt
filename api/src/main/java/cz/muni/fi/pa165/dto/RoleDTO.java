package cz.muni.fi.pa165.dto;

import java.util.Objects;

public class RoleDTO {

    private Long id;
    private String name;
    private String description;

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
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RoleDTO) {
            RoleDTO other = (RoleDTO) obj;
            if (!Objects.equals(name, other.getName())) {
                return false;
            }


            return true;
        }
        return false;
    }

}