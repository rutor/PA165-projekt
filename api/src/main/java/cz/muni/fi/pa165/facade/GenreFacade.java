package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.CreateGenreDTO;
import cz.muni.fi.pa165.dto.GenreDTO;
/**
 * An interface describing the api for genre interactions.
 * @author tyrylu
 */
public interface GenreFacade {
/**
 * Creates a new genre.
 * @param newGenre a description of the new genre
 * @return id of the newly created genre
 */
	public Long createGenre(CreateGenreDTO newGenre);
/**
 * Returns all genres.
 * @return the list of all genres
 */
	public List<GenreDTO> getAllGenres();
/**
 * Gets a genre given its id.
 * @param id the genre's id
 * @return the corresponding genre, if it exists
 */
	public GenreDTO getGenreById(Long id);
/**
 * Returns a genre given its name.
 * @param name the name of the genre to retrieve
 * @return the genre, if it was found
 */
	public GenreDTO getGenreByName(String name);
/**
 * Removes a genre.
 * @param genre a genre to remove
 */
	public void removeGenre(GenreDTO genre);
/**
 * Performs a genre update.
 * @param genre a description of the new state of an existing genre
 */
	public void updateGenre(GenreDTO genre);
}
