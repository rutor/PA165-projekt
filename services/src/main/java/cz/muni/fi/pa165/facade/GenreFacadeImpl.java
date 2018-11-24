package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.dto.CreateGenreDTO;
import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.services.GenreService;

/**
 * Implementation of the genre facade.
 * 
 * @author tyrylu
 *
 */
public class GenreFacadeImpl implements GenreFacade {
	@Inject
	private GenreService genreService;

	@Inject
	private BeanMappingService mappingService;

	@Override
	public Long createGenre(CreateGenreDTO newGenre) {
		Genre mappedGenre = mappingService.mapTo(newGenre, Genre.class);
		genreService.create(mappedGenre);
		return mappedGenre.getId();
	}

	@Override
	public List<GenreDTO> getAllGenres() {
		return mappingService.mapTo(genreService.findAll(), GenreDTO.class);
	}

	@Override
	public GenreDTO getGenreById(Long id) {
		return mappingService.mapTo(genreService.findById(id), GenreDTO.class);
	}

	@Override
	public GenreDTO getGenreByName(String name) {
		return mappingService.mapTo(genreService.findByName(name), GenreDTO.class);
	}

	@Override
	public void removeGenre(Long genreId) {
		genreService.remove(genreService.findById(genreId));
	}

	@Override
	public void updateGenre(GenreDTO genre) {
		genreService.update(mappingService.mapTo(genre, Genre.class));
	}

}
