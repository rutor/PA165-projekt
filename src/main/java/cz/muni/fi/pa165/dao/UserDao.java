
package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;
import java.util.List;

/**
 *
 * @author Robo
 */
public interface UserDao {
    

    public void create(User user);

    public void update(User user);

    public void remove(User user);

    public List<User> findAll();

    public User findById(Long id);

 
}

