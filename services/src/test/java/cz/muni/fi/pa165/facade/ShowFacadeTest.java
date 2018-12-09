package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dto.CreateShowDTO;
import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.dto.ShowDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;
import cz.muni.fi.pa165.services.GenreService;
import cz.muni.fi.pa165.services.ShowService;
import cz.muni.fi.pa165.services.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShowFacadeTest extends AbstractTestNGSpringContextTests {
	@Inject
	private ShowFacade facade;
	@Inject
	private GenreService genreService;
	@Inject
	private ShowService showService;

	@Test
	public void testCreate() {
		Genre g = TestUtils.createGenre("Smuteční píseň", "Byly, jsou, a asi budou");
		g.setId(null);
		genreService.create(g);
		CreateShowDTO s = new CreateShowDTO();
		s.setName("Píseň studenta");
		s.setDescription("Zvláště povedená parodie reality");
		s.setGenreId(g.getId());
		Long id = facade.createShow(s);
		assertNotNull(id);
		Show showFromDb = showService.findById(id);
		assertEquals(s.getName(), showFromDb.getName());
	}

	@Test
	public void testGetById() {
		Show test = insertShow("Testy a já", "Co se stalo a nestalo", 412, "Komedie", "Víte, to se sejdou...");
		ShowDTO testFromDb = facade.getShowById(test.getId());
		assertDTOAndEntityEquals(testFromDb, test);
	}

	@Test
	public void testGetAll() {
		Show test1 = insertShow("O škole a lidech", "Pravdivá, i když děsivá upomínka", 123, "Horor",
				"Po půlnoci nedívat");
		Show test2 = insertShow("A i oni byli", "Nic nezatajujeme", 24, "Horory a strašidelné", "Po půlnoci nedívat");
		List<ShowDTO> shows = facade.getAllShows();
		assertEquals(2, shows.size());
		assertDTOAndEntityEquals(shows.get(0), test1);
		assertDTOAndEntityEquals(shows.get(1), test2);
	}

	@Test
	public void testGetAllByGenre() {
		Show test = insertShow("O škole a lidech", "Pravdivá, i když děsivá upřímná", 123, "Horor",
				"Po půlnoci nedívat");
		Show test2 = TestUtils.createShow("Ve větru", "I tam se dějí věci",
				TestUtils.createGenre("Komedie", "Je, byla, bude"), 123);
		test2.setId(null);
		;
		test2.getGenre().setId(test.getGenre().getId());
		showService.create(test2);
		insertShow("Pro poslední naději", "Zbyly jen vzpomínky", 221, "Horor a katastrofické", "Radši odejděte");
		List<ShowDTO> matching = facade.getAllShowsByGenreId(test.getGenre().getId());
		assertEquals(2, matching.size());
		assertDTOAndEntityEquals(matching.get(0), test);
		assertDTOAndEntityEquals(matching.get(1), test2);
	}

	@Test
	public void testRemove() {
		Show test1 = insertShow("O škole a lidech", "Pravdivá, i když děsivá upomínka", 123,
				"Horor a další předpůlnoční", "Po půlnoci nedívat");
		Show test2 = insertShow("A i oni byli", "Nic nezatajujeme", 24, "Horor", "Po půlnoci nedívat");
		facade.removeShow(test2.getId());
		List<Show> shows = showService.findAll();
		assertEquals(shows.size(), 1);
		assertEquals(test1, shows.get(0));
	}

	@Test
	public void testUpdate() {
		Show test = insertShow("Co stalo se před půlnocí", "Příběh ne úplně vydařeného školního projektu", 144,
				"Tragikomedie", "I tak to někdy dopadá");
		GenreDTO g = new GenreDTO();
		ShowDTO s = new ShowDTO();
		g.setName(test.getGenre().getName());
		g.setId(test.getGenre().getId());
		g.setDescription(test.getGenre().getDescription());
		s.setName(test.getName());
		s.setId(test.getId());
		s.setDescription("Zvláště povedená parodie reality");
		s.setGenre(g);
		facade.updateShow(s);
		assertDTOAndEntityEquals(s, showService.findById(s.getId()));
	}

	private void assertDTOAndEntityEquals(GenreDTO dto, Genre entity) {
		assertEquals(entity.getId(), dto.getId());
		assertEquals(entity.getName(), dto.getName());
		assertEquals(entity.getDescription(), dto.getDescription());
	}

	private void assertDTOAndEntityEquals(ShowDTO dto, Show entity) {
		assertEquals(entity.getId(), dto.getId());
		assertEquals(entity.getName(), dto.getName());
		assertEquals(entity.getDescription(), dto.getDescription());
		assertEquals(dto.getDuration(), entity.getDuration());
		assertDTOAndEntityEquals(dto.getGenre(), entity.getGenre());
	}

	private Show insertShow(String name, String description, int duration, String genreName, String genreDescription) {
		Genre g = new Genre();
		g.setName(genreName);
		g.setDescription(genreDescription);
		genreService.create(g);
		Show s = new Show();
		s.setName(name);
		s.setDescription(description);
		s.setDuration(duration);
		s.setGenre(g);
		showService.create(s);
		return s;
	}
}