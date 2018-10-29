package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.Genre;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GenreDaoTest {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private GenreDao dao;

    private Genre genre;

    public Genre createGenre(String name, String description) {
        Genre g = new Genre();
        g.setName(name);
        g.setDescription(description);
        return g;
    }

    public void removeGenre(Genre g) {
        if (g != null) {
            em.remove(g);
        }
    }

    @Before
    public void setup() {
        genre = createGenre("Lorem", "Dolor");
        em.persist(genre);
    }

    @After
    public void cleanup() {
        removeGenre(genre);
    }

    @Test
    public void createTest() {
        Genre genreLocal = createGenre("XXX", "xxx");
        dao.create(genreLocal);
        Genre genreFromDb = em.find(Genre.class, genreLocal.getId());
        assertEquals(genreLocal, genreFromDb);
    }

    @Test
    public void removeTest() {
        Genre genreLocal = createGenre("XXX", "xxx");
        em.persist(genreLocal);
        dao.remove(genreLocal);
        Genre genreFromDb = em.find(Genre.class, genreLocal.getId());
        assertNull(genreFromDb);
    }

    @Test
    public void findAllTest() {
        Genre genreLocal = createGenre("XXX", "xxx");
        em.persist(genreLocal);
        List<Genre> genres = dao.findAll();
        assertEquals(2, genres.size());
        assertEquals(genre, genres.get(0));
        assertEquals(genreLocal, genres.get(1));
    }

    @Test
    public void findByIdTest() {
        Genre genreFromDb = dao.findById(genre.getId());
        assertEquals(genre, genreFromDb);
    }

    @Test
    public void findByNameTest() {
        Genre genreFromDb = dao.findByName(genre.getName());
        assertEquals(genre, genreFromDb);
    }

    @Test
    public void updateTest() {
        Genre genreFromDb = em.find(Genre.class, genre.getId());
        assertEquals(genre, genreFromDb);

        genre.setName("Amet");
        assertEquals("Amet", genre.getName());
        dao.update(genre);

        Genre showFromDbAfterUpdate = em.find(Genre.class, genre.getId());
        assertEquals(genre, showFromDbAfterUpdate);
        assertEquals("Amet", showFromDbAfterUpdate.getName());
    }
}
