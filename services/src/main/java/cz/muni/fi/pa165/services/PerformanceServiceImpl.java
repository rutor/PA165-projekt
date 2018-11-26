package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.dao.PerformanceDao;
import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.entity.Performance;
import cz.muni.fi.pa165.entity.Show;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author xtrnkal
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Inject
    private PerformanceDao performanceDao;

    @Override
    public Long create(Performance performance) {
        performanceDao.create(performance);
        return performance.getId();
    }

    @Override
    public Performance findById(Long id) {
        return performanceDao.findById(id);
    }

    @Override
    public List<Performance> findAll() {
        return performanceDao.findAll();
    }

    @Override
    public List<Performance> findAllByHall(Hall hall) {
        return performanceDao.findAllByHall(hall);
    }

    @Override
    public List<Performance> findAllByShow(Show show) {
        return performanceDao.findAllByShow(show);
    }

    @Override
    public void remove(Performance performance) {
        performanceDao.remove(performance);
    }

    @Override
    public void update(Performance performance) {
        performanceDao.update(performance);
    }
}
