package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.entity.User;
import java.util.List;

import javax.inject.Named;
import javax.persistence.*;

import org.springframework.stereotype.Repository;





@Repository
@Named
public class UserDaoImpl implements UserDao {
    
      private static final String SELECT_QUERY = "SELECT g from " + User.TABLE_NAME;
    
    @PersistenceContext
    private EntityManager em;
    @Override
    public void create(User user) {
        em.persist(user);
    }
    @Override
    public void remove(User user) {
        em.remove(user);
    }
    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT g from User g", User.class).getResultList();
    }
    @Override
    public User findById(Long id) {
		return em.find(User.class,  id);
	}
    public List<User> findByRole(Role role) {
        return em.createQuery(SELECT_QUERY + "WHERE role = :role", User.class)
                .setParameter("role", role)
                .getResultList();
    }
    @Override
    public void update(User user) {  em.merge(user);  }

}
