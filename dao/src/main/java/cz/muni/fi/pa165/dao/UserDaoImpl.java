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
    public Users findByName(String lastName) {
        return em.createQuery("select u from Users u where lastName = :lastName", Users.class).setParameter("lastName",  lastName).getSingleResult();
    }


    @Override
    public Users findByEmail(String email) {
        return em.createQuery("select u  from Users u where email = :email", Users.class).setParameter("email",  email).getSingleResult();
    }



}
