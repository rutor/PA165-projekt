package cz.muni.fi.pa165.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Ticket to a specific performance
 * @author Tomas Rudolf
 */
@Entity
@Table(name = Ticket.TABLE_NAME)
public class Ticket {

    /** Name of table for Ticket entities */
    public static final String TABLE_NAME = "Bookings";

    @Getter @Setter
    @NotNull
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FIXME Tomas milestone1 - Uncomment after classes are in repository
    /*@Getter @Setter
    @NotNull
    @Column(nullable = false)
    private Performance performance;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private User user;*/

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private LocalDate createdAt;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private LocalDate updatedAt;

    /** Persistence constructor */
    private Ticket () {
        // Common constructor
    }

    public Ticket (Long id) {
        this();
        setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o instanceof Ticket) {
            Ticket other = (Ticket) o;
            // FIXME Tomas milestone1 - Uncomment after classes are in repository
            /*if (!Objects.equals(this.performance, other.getPerformance())) { return false; }
            if (!Objects.equals(this.user, other.getUser())) { return false; }*/
            if (!Objects.equals(this.createdAt, other.getCreatedAt())) { return false; }
            if (!Objects.equals(this.updatedAt, other.getUpdatedAt())) { return false; }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // FIXME Tomas milestone1 - Uncomment after classes are in repository
        return Objects.hash(createdAt, updatedAt /*, performance, user*/ );
    }

}
