package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;

import java.time.LocalDate;
import java.util.List;

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
    // FIXME Tomas milestone1 - Uncomment after classes are in repository
    /*public List<Booking> findByPerformance(Performance performance);
    public List<Booking> findByUser(User user);*/
    public void update(Booking booking);
    public void delete(Booking booking);
}
