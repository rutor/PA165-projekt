package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.*;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;

@Repository
@Named
public class ShowDaoImpl implements ShowDao {
@PersistenceContext
private EntityManager em;
	public void create(Show show) {
		em.persist(show);
	}

	public void remove(Show show) {
		em.remove(show);
	}

	public List<Show> findAll() {
		
		return em.createQuery("select s from Show s", Show.class).getResultList();
	}

	public Show findById(Long id) {
		return em.find(Show.class,  id);
	}

	public List<Show> findAllByGenre(Genre genre) {
		return em.createQuery("select s from Show s where genre = :genre", Show.class).setParameter("genre",  genre).getResultList();
	}
}
