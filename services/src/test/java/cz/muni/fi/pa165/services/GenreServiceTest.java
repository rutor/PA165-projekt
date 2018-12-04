package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entity.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GenreServiceTest extends AbstractTestNGSpringContextTests {
@Mock
GenreDao dao;
        @Inject
        @InjectMocks
    private GenreService service;

        private Genre opera;
        private Genre comedy;
    public Genre createGenre(Long id, String name, String description) {
        Genre g = new Genre();
        g.setId(id);
        g.setName(name);
        g.setDescription(description);
        return g;
    }

    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	opera = createGenre(1l, "Opera", "Však víte.");
    	comedy = createGenre(2l, "Komedie", "Byla, je a bude");
    }

    @Test
public void testFindById() {
	when(dao.findById(1l)).thenReturn(opera);
	assertEquals(service.findById(1l), opera);
}

    @Test
    public void testFindAll() {
    	List<Genre> genres = Arrays.asList(opera, comedy);
    	when(dao.findAll()).thenReturn(genres);
    	List<Genre> result = service.findAll();
    	assertEquals(result.size(), 2);
    	assertEquals(genres.get(0), result.get(0));
    	assertEquals(genres.get(1), result.get(1));
    }
@Test
public void testCreate() {
	assertEquals(service.create(opera), new Long(1));
	verify(dao).create(opera);
}
@Test
public void testRemove() {
	service.remove(comedy);
	verify(dao).remove(comedy);
}
@Test
public void testUpdate() {
	service.update(opera);
	verify(dao).update(opera);
}

@Test
public void testFindByName() {
	when(dao.findByName("opera")).thenReturn(opera);
	assertEquals(service.findByName("opera"), opera);
}
}
