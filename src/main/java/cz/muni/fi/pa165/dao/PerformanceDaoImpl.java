package cz.muni.fi.pa165.dao;

import java.util.List;
import javax.persistence.*;
import javax.inject.Named;
import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.*;

/**
 * Implementation of PerformanceDao
 * @author xtrnkal
 */
@Repository
@Named
public class PerformanceDaoImpl implements PerformanceDao {
    @PersistenceContext
    private EntityManager em;
    
    public void create(Performance performance) {
        em.persist(performance);
    }

    public void update(Performance performance) {
        em.merge(performance);
    }
    
    public void remove(Performance performance) {
        em.remove(performance);
    }

    public List<Performance> findAll() {
        return em.createQuery("select p from Performance p", Performance.class).getResultList();
    }

    public Performance findById(Long id) {
        return em.find(Performance.class, id);
    }
    
    public List<Performance> findAllByShow(Show show) {
        return em.createQuery("select p from Performace p where show = :show", Performance.class)
                .setParameter("show", show).getResultList();
    }

    public List<Performance> findAllByHall(Hall hall) {
        return em.createQuery("select p from Performance p where hall := hall", Performance.class)
                .setParameter("hall", hall).getResultList();
    }
}
