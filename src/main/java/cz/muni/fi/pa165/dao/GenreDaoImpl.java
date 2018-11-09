package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.*;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.Genre;
/**
 * Implementation of the genre dao.
 * @author tyrylu
 */
@Repository
@Named
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
@Override
	public Genre findByName(String name) {
	return em.createQuery("select g from Genre g where name = :name", Genre.class).setParameter("name",  name).getSingleResult();
}
@Override
public void update(Genre genre) {
	em.merge(genre);
}
}
