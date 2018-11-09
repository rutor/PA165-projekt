package cz.muni.fi.pa165.dao;

import java.util.List;
import javax.persistence.*;
import javax.inject.Named;
import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.Hall;


/**
 * Implementation of HallDao
 * @author xtrnkal
 */
@Repository
@Named
public class HallDaoImpl implements HallDao {
    @PersistenceContext
    private EntityManager em;

    public void create(Hall hall) {
        em.persist(hall);
    }

    public void update(Hall hall) {
        em.merge(hall);
    }
    
    public void remove(Hall hall) {
        em.remove(hall);
    }

    public List<Hall> findAll() {
        return em.createQuery("select h from Hall h", Hall.class).getResultList();
    }

    public Hall findById(Long id) {
        return em.find(Hall.class, id);
    }
    
}
