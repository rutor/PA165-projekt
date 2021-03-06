package cz.muni.fi.pa165.entity;
import cz.muni.fi.pa165.enums.PaymentStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Booking of seat before it becomes a ticket
 * @author Tomas Rudolf
 */
@Entity
@Table(name = Booking.TABLE_NAME)
public class Booking {

    /** Name of table for Booking entities */
    public static final String TABLE_NAME = "Bookings";

    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAYED;

    @Getter @Setter
    @OneToOne(targetEntity = Ticket.class)
    private Ticket ticket;

    @Getter @Setter
    private String description;

    @Getter @Setter
    @NotNull
    @JoinColumn(nullable = false)
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
    private LocalDate createdAt = LocalDate.now();

    @Getter @Setter
    @NotNull
    @Column(nullable = false)
    private LocalDate updatedAt = LocalDate.now();

    /** Persistence constructor */
    public Booking() {
    }

    public Booking(Long id) {
        this();
        setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o instanceof Booking) {
            Booking other = (Booking) o;
            if (!Objects.equals(this.paymentStatus, other.getPaymentStatus())) { return false; }
            if (!Objects.equals(this.ticket, other.getTicket())) { return false; }
            if (!Objects.equals(this.description, other.getDescription())) { return false; }
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
        return Objects.hash(paymentStatus, ticket, description, performance, user, createdAt, updatedAt);
    }
}


