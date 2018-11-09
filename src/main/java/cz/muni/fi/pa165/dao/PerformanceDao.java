package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.*;

/**
 * Interface of Performance dao
 * @author xtrnkal
 */
public interface PerformanceDao {
    
    public void create(Performance performance);
    public void update(Performance performance);
    public void remove(Performance performance);
    public List<Performance> findAll();
    public Performance findById(Long id);
    public List<Performance> findAllByShow(Show show);
    public List<Performance> findAllByHall(Hall hall);
    
}
