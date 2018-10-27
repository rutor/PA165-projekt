package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.entity.Performance;
import cz.muni.fi.pa165.entity.Show;

/**
 * Interface of DAO for Performance entity
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
