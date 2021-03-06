package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.TicketStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Ticket to a specific performance
 * @author Tomas Rudolf
 */
@Entity
@Table(name = Ticket.TABLE_NAME)
public class Ticket {

    /** Name of table for Ticket entities */
    public static final String TABLE_NAME = "Tickets";

    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @NotNull
    @Column(nullable = false, unique = true)
    private UUID barcode;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private TicketStatus status;

    @Getter @Setter
    @NotNull
    @JoinColumn(nullable=false)
    @ManyToOne
    private Performance performance;

    @Getter @Setter
    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private Users user;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private LocalDate createdAt;

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private LocalDate updatedAt;

    /** Persistence constructor */
    public Ticket () {
        setCreatedAt(LocalDate.now());
        setUpdatedAt(LocalDate.now());
        setBarcode(UUID.randomUUID());
        setStatus(TicketStatus.NOT_USED);
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
            if (!Objects.equals(this.barcode, other.getBarcode())) { return false; }
            if (!Objects.equals(this.performance, other.getPerformance())) { return false; }
            if (!Objects.equals(this.user, other.getUser())) { return false; }
            if (!Objects.equals(this.createdAt, other.getCreatedAt())) { return false; }
            if (!Objects.equals(this.updatedAt, other.getUpdatedAt())) { return false; }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, updatedAt, barcode, performance, user);
    }

    public static Ticket createFromBooking(Booking booking) {
        Ticket ticket = new Ticket();
        ticket.setUser(booking.getUser());
        ticket.setPerformance(booking.getPerformance());
        return ticket;
    }

}
