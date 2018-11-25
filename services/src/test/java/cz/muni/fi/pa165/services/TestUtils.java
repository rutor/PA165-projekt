package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.TicketStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TestUtils {

    public static Ticket createTicket(@NotNull Performance performance, @NotNull Users user) {
        Ticket ticket = new Ticket();
        ticket.setId((long)Math.random()*1000);
        ticket.setPerformance(performance);
        ticket.setStatus(TicketStatus.NOT_USED);
        ticket.setUser(user);
        ticket.setCreatedAt(LocalDate.now());
        ticket.setUpdatedAt(LocalDate.now());
        return ticket;
    }

    public static Performance createPerformance(@NotNull String description, @NotNull Float price, @NotNull Hall hall, @NotNull Show show, @NotNull LocalDate startDate) {
        Performance performance = new Performance();
        performance.setDescription(description);
        performance.setPrice(price);
        performance.setHall(hall);
        performance.setShow(show);
        performance.setStartDate(startDate);
        return performance;
    }

    public static Hall createHall(@NotNull String name, @NotNull String address, @NotNull Long capacity) {
        Hall hall = new Hall();
        hall.setName(name);
        hall.setAddress(address);
        hall.setCapacity(capacity);
        return hall;
    }

    public static Show createShow(@NotNull String name, @NotNull String description, @NotNull Genre genre, @NotNull Integer duration) {
        Show show = new Show();
        show.setId((long)Math.random()*1000);
        show.setName(name);
        show.setDescription(description);
        show.setGenre(genre);
        show.setDuration(duration);
        return show;
    }
    public static Genre createGenre(String name, String description) {
        Genre genre = new Genre();
        genre.setId((long)Math.random()*1000);
        genre.setName(name);
        genre.setDescription(description);
        return genre;
    }

    public static Users createUser(@NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull String password, @NotNull Role role, @NotNull LocalDate createdAt, @NotNull LocalDate updatedAt) {
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        return user;
    }

    public static Role createRole(@NotNull String name, @NotNull String description) {
        Role role = new Role();
        role.setId((long)Math.random()*1000);
        role.setName(name);
        role.setDescription(description);
        return role;
    }

}
