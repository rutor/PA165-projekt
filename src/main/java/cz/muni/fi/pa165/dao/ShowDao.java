package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;

/**
 * The database access interface for shows. 
  */
public interface ShowDao {
/**
 * Adds a show to the database.
 * @param show the show to persist
 */
	public void create(Show show);
/**
 * Removes a show from the database.
 * @param show the show to remove
 */
	public void remove(Show show);
/**
 * Returns all shows stored in the database.
 * @return the entire show collection as a list
 */
	public List<Show> findAll();
/**
 * Returns a show using its id.
 * @param id the id to search for
 * @return the show, or null if it does not exist
 */
	public Show findById(Long id);
	/**
	 * Returns all shows which belong to the specified genre.
	 * @param genre the genre we're interested in
	 * @return list of all shows belonging to this genre
	 */
	public List<Show> findAllByGenre(Genre genre);
/**
 * Does an explicit update of a show object. 
 * @param show the show to apply the state from
 */
	public void update(Show show);
}
