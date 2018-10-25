package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.Genre;

/**
 * The database access interface for genres. 
  */
public interface GenreDao {
/**
 * Adds a genre to the database.
 * @param genre the genre to persist
 */
	public void create(Genre genre);
/**
 * Removes a genre from the database.
 * @param genre the genre to remove
 */
	public void remove(Genre genre);
/**
 * Returns all genres stored in the database.
 * @return the entire genre collection as a list (do not use with millions of rows)
 */
	public List<Genre> findAll();
/**
 * Returns a genre using its id.
 * @param id the id to search for
 * @return the genre, or null if it does not exist
 */
	public Genre findById(Long id);
}
