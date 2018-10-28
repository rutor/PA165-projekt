package cz.muni.fi.pa165.entity;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * User class
 * @author Robert Dudas
 */
@Entity
public class User {

    public static String TABLE_NAME;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Role role;

    @Column(nullable = false, unique = true)
    @NotNull
    private String firstName;

    @Column(nullable = false, unique = true)
    @NotNull
    private String lastName;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$")
    @NotNull
    private String email;


    @Column(nullable = false, unique = true)
    @NotNull
    private String password;


    private LocalDate createdAt;


    private LocalDate updatedAt;



    private User(){};

    public User(Long id) {
        this();
        setId(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
        createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            User other = (User) obj;
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

            if (!Objects.equals(createdAt, other.getCreatedAt())) {
                return false;
            }

            if (!Objects.equals(updatedAt, other.getUpdatedAt())) {
                return false;
            }

            if (!Objects.equals(role, other.getRole())) {
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole(), getFirstName(), getLastName(), getEmail(), getPassword(), getCreatedAt(), getUpdatedAt());
    }
}
