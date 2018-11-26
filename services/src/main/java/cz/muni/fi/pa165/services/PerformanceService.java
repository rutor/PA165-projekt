package cz.muni.fi.pa165.services;

import java.util.List;
import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.entity.Performance;
import cz.muni.fi.pa165.entity.Show;

/**
 *
 * @author xtrnkal
 */
public interface PerformanceService {

    public Long create(Performance performance);

    public Performance findById(Long id);

    public List<Performance> findAll();

    public List<Performance> findAllByHall(Hall hall);

    public List<Performance> findAllByShow(Show show);

    public void remove(Performance performance);

    public void update(Performance performance);
}
