
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;
import java.util.List;

/**
 *
 * @author Robo
 */
public interface UserDao {

    public void create(Users user);

    public void update(Users user);

    public void remove(Users user);

    public List<Users> findAll();

    public Users findById(Long id);

    public Users findByName(String name);

    public Users findByEmail(String email);

    public  List<Users> findByRole(Role role);
 
}

