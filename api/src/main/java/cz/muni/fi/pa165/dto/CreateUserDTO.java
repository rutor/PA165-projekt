package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;


public class CreateUserDTO {


    @NotNull
    private Long roleId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String  email;
    @NotNull
    private String password;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName ,password,email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CreateUserDTO) {
            CreateUserDTO other = (CreateUserDTO) obj;
            if (!Objects.equals(firstName, other.getFirstName())) {
                return false;
            }
            if (!Objects.equals(lastName, other.getLastName())) {
                return false;
            }
            if (!Objects.equals(email, other.getEmail())) {
                return false;
            }

            if (!Objects.equals(password, other.getPassword())) {
                return false;
            }

            return true;
        }
        return false;
    }
}