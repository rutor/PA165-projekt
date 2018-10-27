package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;
import javax.inject.Named;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
@Named
public class TicketDaoImpl implements TicketDao {

    /** Start of selection query */
    private static final String SELECT_QUERY = "SELECT t from " + Ticket.TABLE_NAME;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Ticket ticket) {
        em.persist(ticket);
    }

    @Override
    public Ticket findById(Long id) {
        return em.createQuery(" WHERE id = :id", Ticket.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Ticket> findAll() {
        return em.createQuery(SELECT_QUERY, Ticket.class)
                .getResultList();
    }

    @Override
    public List<Ticket> findByCreationDate(LocalDate creationDate) {
        return em.createQuery(SELECT_QUERY + " WHERE createdAt = :creationDate", Ticket.class)
                .setParameter("creationDate", creationDate)
                .getResultList();
    }

    @Override
    public List<Ticket> findByUpdateDate(LocalDate updateDate) {
        return em.createQuery(SELECT_QUERY + " WHERE updatedAt = :updateDate", Ticket.class)
                .setParameter("updateDate", updateDate)
                .getResultList();
    }

    // FIXME Tomas milestone1 - Uncomment after classes are in repository
    /*@Override
    public List<Ticket> findByPerformance(Performance performance) {
        return em.createQuery(SELECT_QUERY + " WHERE performance = :performance", Ticket.class)
                .setParameter("performance", performance)
                .getResultList();
    }

    @Override
    public List<Ticket> findByUser(User user) {
        return em.createQuery(SELECT_QUERY + " WHERE user = :user", Ticket.class)
                .setParameter("user", user)
                .getResultList();
    }*/

    @Override
    public void update(Ticket ticket) {
        em.merge(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        em.remove(ticket);
    }
}
