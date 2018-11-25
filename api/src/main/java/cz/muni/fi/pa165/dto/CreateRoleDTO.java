package cz.muni.fi.pa165.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;

public class CreateRoleDTO {

    @NotNull
    private String name;
    @NotNull
    private String description;

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
        return Objects.hash(name,description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof CreateRoleDTO) {
            CreateRoleDTO other = (CreateRoleDTO) obj;
            if (!Objects.equals(name, other.getName())) {
                return false;
            }
            if (!Objects.equals(description, other.getDescription())) {
                return false;
            }


            return true;
        }
        return false;
    }

}