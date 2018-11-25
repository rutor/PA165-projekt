package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.entity.Users;
import java.util.List;

import javax.inject.Named;
import javax.persistence.*;

import org.springframework.stereotype.Repository;





@Repository
@Named
public class UserDaoImpl implements UserDao {
    
      private static final String SELECT_QUERY = "SELECT g from " + Users.TABLE_NAME + " g ";
    
    @PersistenceContext
    private EntityManager em;
    @Override
    public void create(Users user) {
        em.persist(user);
    }
    @Override
    public void remove(Users user) {
        em.remove(user);
    }
    @Override
    public List<Users> findAll() {
        return em.createQuery(SELECT_QUERY, Users.class).getResultList();
    }
    @Override
    public Users findById(Long id) {
		return em.find(Users.class,  id);
	}
    public List<Users> findByRole(Role role) {
        return em.createQuery(SELECT_QUERY + "WHERE role = :role", Users.class)
                .setParameter("role", role)
                .getResultList();
    }
    @Override
    public void update(Users user) {  em.merge(user);  }

    @Override
    public Users findByName(String name) {
        return em.createQuery("select u from Users where name = :name", Users.class).setParameter("name",  name).getSingleResult();
    }

}
