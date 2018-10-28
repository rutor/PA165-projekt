package cz.muni.fi.pa165.entity;



import javax.persistence.*;
import java.util.*;
import java.util.UUID;

/**
 * User class
 * @author Robert Dudas
 */
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Role role;


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$")
    @NotNull
    private String email;

    @NotNull
    private String password;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date updatedAt;


    /** Persistence constructor */
    private User() {
        setBarcode(UUID.randomUUID());
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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
