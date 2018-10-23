package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.entity.Genre;

public class GenreDaoImpl implements GenreDao {
@PersistenceContext
private EntityManager em;
	
public void create(Genre genre) {
		em.persist(genre);
	}

	public void remove(Genre genre) {
		em.remove(genre);
	}

	public List<Genre> findAll() {
		return em.createQuery("select g from Genre g", Genre.class).getResultList();
	}

	public Genre findById(Long id) {
		return em.find(Genre.class, id);
	}

}
