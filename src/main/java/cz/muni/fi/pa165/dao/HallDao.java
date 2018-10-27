package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.Hall;

/**
 * Interface of Hall dao
 * @author xtrnkal
 */
public interface HallDao {

    public void create(Hall hall);

    public void update(Hall hall);
    
    public void remove(Hall hall);

    public List<Hall> findAll();

    public Hall findById(Long id);
    
}
