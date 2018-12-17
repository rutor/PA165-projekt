package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.dto.CreateShowDTO;
import cz.muni.fi.pa165.dto.ShowDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;
import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.services.GenreService;
import cz.muni.fi.pa165.services.ShowService;

/**
 * Implementation of the show facade.
 *
 * @author tyrylu
 */
@Service
@Transactional
public class ShowFacadeImpl implements ShowFacade {

    @Inject
    private ShowService showService;

    @Inject
    private GenreService genreService;

    @Inject
    private BeanMappingService mappingService;

    @Override
    public Long createShow(CreateShowDTO newShow) {
        Show mappedShow = mappingService.mapTo(newShow, Show.class);
        mappedShow.setGenre(genreService.findById(newShow.getGenreId()));
        showService.create(mappedShow);
        return mappedShow.getId();
    }

    @Override
    public List<ShowDTO> getAllShows() {
        return mappingService.mapTo(showService.findAll(), ShowDTO.class);
    }

    @Override
    public ShowDTO getShowById(Long id) {
        return mappingService.mapTo(showService.findById(id), ShowDTO.class);
    }

    @Override
    public List<ShowDTO> getAllShowsByGenreId(Long genreId) {
        Genre genre = genreService.findById(genreId);
        return mappingService.mapTo(showService.findAllByGenre(genre), ShowDTO.class);
    }

    @Override
    public void removeShow(Long showId) {
        showService.remove(showService.findById(showId));
    }

    @Override
    public void updateShow(ShowDTO show) {
        showService.update(mappingService.mapTo(show, Show.class));
    }

}
