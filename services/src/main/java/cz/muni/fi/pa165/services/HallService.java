package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.entity.Hall;
import java.util.List;

/**
 *
 * @author xtrnkal
 */
public interface HallService {

    public Long create(Hall hall);

    public Hall findById(Long id);

    public List<Hall> findAll();

    public void remove(Hall hall);

    public void update(Hall hall);
    
    public Hall findByName(String name);
}
