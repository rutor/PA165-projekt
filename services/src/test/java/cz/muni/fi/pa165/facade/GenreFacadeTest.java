package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.EntityUtils;
import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dto.CreateGenreDTO;
import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.services.GenreService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GenreFacadeTest extends AbstractTestNGSpringContextTests {
	@Inject
	private GenreFacade facade;
	@Inject
	private GenreService service;

	@PersistenceContext
	private EntityManager em;
	private CreateGenreDTO newOpera;

	public CreateGenreDTO getCreateGenreDTO(String name, String description) {
		CreateGenreDTO cg = new CreateGenreDTO();
		cg.setName(name);
		cg.setDescription(description);
		return cg;
	}

	@Before
	public void setup() {
		newOpera = getCreateGenreDTO("Opera", "Však víte.");
	}

	@Test @Ignore
	public void testCreateGenre() {
		Long id = facade.createGenre(newOpera);
		assertNotNull(id);
		Genre operaFromDb = service.findById(id);
		System.out.println(newOpera);
		System.out.println(operaFromDb);
		assertEquals(newOpera.getName(), operaFromDb.getName());
		assertEquals(newOpera.getDescription(), operaFromDb.getDescription());
	}

	@Test @Ignore
	public void testGetById() {
		Genre test = EntityUtils.createGenre("This is", "a test");
		test.setId(null);
		Long id = service.create(test);
		GenreDTO genreFromDb = facade.getGenreById(id);
		assertDTOAndEntityEquals(genreFromDb, test);
	}

	@Test @Ignore
	public void testGetAll() {
		Genre test1 = EntityUtils.createGenre("This is", "a test");
		test1.setId(null);
		service.create(test1);
		Genre test2 = EntityUtils.createGenre("This is also", "a test");
		test2.setId(null);
		service.create(test2);
		List<GenreDTO> genres = facade.getAllGenres();
		assertEquals(2, genres.size());
		assertDTOAndEntityEquals(genres.get(0), test1);
		assertDTOAndEntityEquals(genres.get(1), test2);
	}

	@Test @Ignore
	public void testGetByName() {
		Genre test = EntityUtils.createGenre("opera", "Dávná");
		test.setId(null);
		service.create(test);
		GenreDTO testFromDb = facade.getGenreByName(test.getName());
		assertDTOAndEntityEquals(testFromDb, test);
	}

	@Test @Ignore
	public void testRemove() {
		Genre test1 = EntityUtils.createGenre("This is", "a test");
		test1.setId(null);
		service.create(test1);
		Genre test2 = EntityUtils.createGenre("This is also", "a test");
		test2.setId(null);
		service.create(test2);
		facade.removeGenre(test2.getId());
		List<Genre> genres = service.findAll();
		assertEquals(genres.size(), 1);
		assertEquals(test1, genres.get(0));
	}

	@Test @Ignore
	public void testUpdate() {
		Genre test1 = EntityUtils.createGenre("This is", "a test");
		test1.setId(null);
		service.create(test1);
		GenreDTO newTest1 = new GenreDTO();
		newTest1.setName(test1.getName());
		newTest1.setId(test1.getId());
		newTest1.setDescription("We're describing you differently.");
		facade.updateGenre(newTest1);
		assertDTOAndEntityEquals(newTest1, service.findById(newTest1.getId()));
	}

	private void assertDTOAndEntityEquals(GenreDTO dto, Genre entity) {
		assertEquals(entity.getId(), dto.getId());
		assertEquals(entity.getName(), dto.getName());
		assertEquals(entity.getDescription(), dto.getDescription());
	}

}
