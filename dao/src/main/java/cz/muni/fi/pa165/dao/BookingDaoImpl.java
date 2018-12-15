package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.PaymentStatus;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Implementation of DAO for Booking entity
 * @author Tomas Rudolf
 */
@Repository
@Named
public class BookingDaoImpl implements BookingDao {

    /** Start of selection query */
    private static final String SELECT_QUERY = "SELECT b from " + Booking.class.getSimpleName() + " b ";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Booking booking) {
        em.persist(booking);
    }

    @Override
    public Booking findById(Long id) {
        try {
            return em.createQuery(SELECT_QUERY + " where id = :id", Booking.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Booking> findAll() {
        try {
            return em.createQuery(SELECT_QUERY, Booking.class)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Booking findByTicket(Ticket ticket) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE ticket = :ticket", Booking.class)
                    .setParameter("ticket", ticket)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Booking> findByPaymentStatus(PaymentStatus paymentStatus) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE paymentStatus = :paymentStatus", Booking.class)
                    .setParameter("paymentStatus", paymentStatus)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Booking> findByCreationDate(LocalDate creationDate) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE createdAt = :creationDate", Booking.class)
                    .setParameter("creationDate", creationDate)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Booking> findByUpdateDate(LocalDate updateDate) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE updatedAt = :updateDate", Booking.class)
                    .setParameter("updateDate", updateDate)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Booking> findByPerformance(Performance performance) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE performance = :performance", Booking.class)
                    .setParameter("performance", performance)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Booking> findByUser(Users user) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE user = :user", Booking.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Booking booking) {
        em.merge(booking);
    }

    @Override
    public void delete(Booking booking) {
        em.remove(booking);
    }
}
