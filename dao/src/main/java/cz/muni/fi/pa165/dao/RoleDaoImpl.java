package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.*;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.Role;

@Repository
@Named
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Role role) {
        em.persist(role);
    }

    @Override
    public void remove(Role role) {
        em.remove(role);
    }

    @Override
    public List<Role> findAll() { return em.createQuery("select g from Role g", Role.class).getResultList();   }

    @Override
    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public void update(Role role) { em.merge(role); }

    @Override
    public Role findByName(String name) {
        try {
            return em.createQuery("select u from Role u  where name = :name", Role.class).setParameter("name",  name).getSingleResult();
        }
        catch (NoResultException ex) {
            return null;
        }
    }
}
