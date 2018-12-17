package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.Objects;



public class UserDTO {

    private Long id;
    private RoleDTO role;
    private String firstName;
    private String lastName;
    private String  email;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName ,password,email,createdAt,updatedAt,role);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserDTO) {
            UserDTO other = (UserDTO) obj;
            if (!Objects.equals(firstName, other.getFirstName())) {
                return false;
            }
            if (!Objects.equals(lastName, other.getLastName())) {
                return false;
            }
            if (!Objects.equals(password, other.getPassword())) {
                return false;
            }
            if (!Objects.equals(email, other.getEmail())) {
                return false;
            }
            if (!Objects.equals(createdAt, other.getCreatedAt())) {
                return false;
            }
            if (!Objects.equals(role, other.getRole())) {
                return false;
            }
            if (!Objects.equals(updatedAt, other.getUpdatedAt())) {
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserDTO{" +firstName+','+lastName+','+email+','+createdAt+','+updatedAt+','+role.getName()+'}';

    }
}
