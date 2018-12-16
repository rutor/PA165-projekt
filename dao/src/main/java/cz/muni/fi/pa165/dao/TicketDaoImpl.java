package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;
import javax.inject.Named;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
@Named
public class TicketDaoImpl implements TicketDao {

    /** Start of selection query */
    private static final String SELECT_QUERY = "SELECT t from " + Ticket.class.getSimpleName() + " t ";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Ticket ticket) {
        em.persist(ticket);
    }

    @Override
    public Ticket findById(Long id) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE id = :id", Ticket.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Ticket> findAll() {
        try {
            return em.createQuery(SELECT_QUERY, Ticket.class)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Ticket findByBarcode(UUID barcode) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE barcode = :barcode", Ticket.class)
                    .setParameter("barcode", barcode)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Ticket> findByCreationDate(LocalDate creationDate) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE createdAt = :creationDate", Ticket.class)
                    .setParameter("creationDate", creationDate)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ticket> findByUpdateDate(LocalDate updateDate) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE updatedAt = :updateDate", Ticket.class)
                    .setParameter("updateDate", updateDate)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ticket> findByPerformance(Performance performance) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE performance = :performance", Ticket.class)
                    .setParameter("performance", performance)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ticket> findByUser(Users user) {
        try {
            return em.createQuery(SELECT_QUERY + " WHERE user = :user", Ticket.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Ticket ticket) {
        em.merge(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        em.remove(ticket);
    }
}
