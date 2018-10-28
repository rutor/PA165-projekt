package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Role;
import java.util.List;

import javax.inject.Named;
import javax.persistence.*;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.User;

@Repository
@Named
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public void remove(User user) {
        em.remove(user);
    }
/*
    public List<User> findAll() {
        return em.createQuery("select g from User g", User.class).getResultList();
    }

    public User findById(Long id) {
		return em.find(User.class,  id);
	}
*/
    /*public User findByRole(User user ) {
     //   return ;
    }*/

    @Override
    public void create(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
