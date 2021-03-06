package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.PaymentStatus;

import java.time.LocalDate;
import java.util.*;

/**
 * Interface of DAO for Booking entity
 * @author Tomas Rudolf
 */
public interface BookingDao {
    public void create(Booking booking);
    public Booking findById(Long id);
    public List<Booking> findAll();
    public Booking findByTicket(Ticket ticket);
    public List<Booking> findByPaymentStatus(PaymentStatus paymentStatus);
    public List<Booking> findByCreationDate(LocalDate creationDate);
    public List<Booking> findByUpdateDate(LocalDate updateDate);
    public List<Booking> findByPerformance(Performance performance);
    public List<Booking> findByUser(Users user);
    public void update(Booking booking);
    public void delete(Booking booking);
}
