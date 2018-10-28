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

    public void create(Role role) {
        em.persist(role);
    }

    public void remove(Role role) {
        em.remove(role);
    }

    public List<Role> findAll() {
        return em.createQuery("select g from Genre g", Role.class).getResultList();
    }

    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

}
