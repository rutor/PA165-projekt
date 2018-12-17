package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.PaymentStatus;
import cz.muni.fi.pa165.enums.TicketStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntityUtils {

    public static Ticket createTicket(Long id, @NotNull Performance performance, @NotNull Users user) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setPerformance(performance);
        ticket.setStatus(TicketStatus.NOT_USED);
        ticket.setUser(user);
        ticket.setCreatedAt(LocalDate.now());
        ticket.setUpdatedAt(LocalDate.now());
        return ticket;
    }

    public static Booking createBooking(Long id, @NotNull Users user, @NotNull String description,
                                        @NotNull PaymentStatus paymentStatus, @NotNull Performance performance,
                                        @NotNull LocalDate createdaAt, @NotNull LocalDate updatedAt) {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setUser(user);
        booking.setDescription(description);
        booking.setPaymentStatus(paymentStatus);
        booking.setPerformance(performance);
        booking.setCreatedAt(createdaAt);
        booking.setUpdatedAt(updatedAt);
        booking.setTicket(null);
        return booking;
    }

    public static Performance createPerformance(Long id, @NotNull String description, @NotNull Float price,
                                                @NotNull Hall hall, @NotNull Show show, @NotNull LocalDateTime startDate) {
        Performance performance = new Performance();
        performance.setId(id);
        performance.setDescription(description);
        performance.setPrice(price);
        performance.setHall(hall);
        performance.setShow(show);
        performance.setStartDate(startDate);
        return performance;
    }

    public static Hall createHall(Long id, @NotNull String name, @NotNull String address, @NotNull Long capacity) {
        Hall hall = new Hall();
        hall.setId(id);
        hall.setName(name);
        hall.setAddress(address);
        hall.setCapacity(capacity);
        return hall;
    }

    public static Show createShow(Long id, @NotNull String name, @NotNull String description, @NotNull Genre genre, @NotNull Integer duration) {
        Show show = new Show();
        show.setId(id);
        show.setName(name);
        show.setDescription(description);
        show.setGenre(genre);
        show.setDuration(duration);
        return show;
    }
    public static Genre createGenre(Long id, @NotNull String name, @NotNull String description) {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        genre.setDescription(description);
        return genre;
    }

    public static Users createUser(Long id, @NotNull String firstName, @NotNull String lastName, @NotNull String email,
                                   @NotNull String password, @NotNull Role role, @NotNull LocalDate createdAt,
                                   @NotNull LocalDate updatedAt) {
        Users user = new Users();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        return user;
    }

    public static Role createRole(Long id, @NotNull String name, @NotNull String description) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setDescription(description);
        return role;
    }

}
