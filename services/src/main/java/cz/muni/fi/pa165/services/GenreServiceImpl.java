package cz.muni.fi.pa165.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entity.Genre;

@Service
public class GenreServiceImpl implements GenreService {

	@Inject
	private GenreDao genreDao;
	
	@Override
	public Long create(Genre genre) {
		genreDao.create(genre);
		return genre.getId();
	}

	@Override
	public Genre findById(Long id) {
		return genreDao.findById(id);
	}

	@Override
	public List<Genre> findAll() {
		return genreDao.findAll();
	}

	@Override
	public Genre findByName(String name) {
		return genreDao.findByName(name);
	}

	@Override
	public void remove(Genre genre) {
genreDao.remove(genre);
	}

	@Override
	public void update(Genre genre) {
genreDao.update(genre);
	}

}
