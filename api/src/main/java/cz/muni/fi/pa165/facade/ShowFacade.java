package cz.muni.fi.pa165.facade;

import java.util.List;

import cz.muni.fi.pa165.dto.CreateShowDTO;
import cz.muni.fi.pa165.dto.ShowDTO;

/**
 * An interface describing the facade for show interactions.
 * 
 * @author tyrylu
 */
public interface ShowFacade {
	/**
	 * Creates a new show.
	 * 
	 * @param newShow a description of the show to create
	 * @return the id of the newly created show
	 */
	public Long createShow(CreateShowDTO newShow);

	/**
	 * Gets all shows.
	 * 
	 * @return list of all shows
	 */
	public List<ShowDTO> getAllShows();

	/**
	 * Returns a show given its id.
	 * 
	 * @param id the id of the show
	 * @return the show, if it exists
	 */
	public ShowDTO getShowById(Long id);

	/**
	 * Returns all shows of a particular genre.
	 * 
	 * @param genreId the id of the genre
	 * @return the list of shows
	 */
	public List<ShowDTO> getAllShowsByGenreId(Long genreId);

	/**
	 * Removes a show.
	 * 
	 * @param showId an id of show to remove
	 */
	public void removeShow(Long showId);

	/**
	 * Updates a show.
	 * 
	 * @param show details of the new values for an existing show
	 */
	public void updateShow(ShowDTO show);
}
