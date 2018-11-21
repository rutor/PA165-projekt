package cz.muni.fi.pa165.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.dao.ShowDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;

@Service
public class ShowServiceImpl implements ShowService {

	@Inject
	private ShowDao showDao;
	@Override
	public Long create(Show show) {
showDao.create(show);
		return show.getId();
	}

	@Override
	public Show findById(Long id) {
		return showDao.findById(id);
	}

	@Override
	public List<Show> findAll() {
		return showDao.findAll();
	}

	@Override
	public List<Show> findAllByGenre(Genre genre) {
		return showDao.findAllByGenre(genre);
	}

	@Override
	public void remove(Show show) {
showDao.remove(show);
	}

	@Override
	public void update(Show show) {
showDao.update(show);
	}

}
