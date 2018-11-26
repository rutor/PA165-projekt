package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.dao.HallDao;
import cz.muni.fi.pa165.entity.Hall;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author xtrnkal
 */
@Service
public class HallServiceImpl implements HallService {

    @Inject
    private HallDao hallDao;

    @Override
    public Long create(Hall hall) {
        hallDao.create(hall);
        return hall.getId();
    }

    @Override
    public Hall findById(Long id) {
        return hallDao.findById(id);
    }

    @Override
    public List<Hall> findAll() {
        return hallDao.findAll();
    }

    @Override
    public void remove(Hall hall) {
        hallDao.remove(hall);
    }

    @Override
    public void update(Hall hall) {
        hallDao.update(hall);
    }

}
