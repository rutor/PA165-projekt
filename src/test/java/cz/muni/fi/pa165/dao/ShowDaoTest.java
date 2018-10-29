package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShowDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ShowDao dao;

    private Show show;
    private Genre genre;

    Show createShow(String name, String description, Genre genre) {
        Show s = new Show();
        s.setName(name);
        s.setDescription(description);
        s.setGenre(genre);
        return s;
    }

    void removeShow(Show s) {
        if (s != null) {
            em.remove(s);
        }
    }

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
        show = createShow("Lorem", "Dolor", genre);
        em.persist(show);
    }

    @After
    public void cleanup() {
        removeShow(show);
        removeGenre(genre);
    }

    @Test
    public void createTest() {
        Show showLocal = new Show();
        showLocal.setName("AAA");
        showLocal.setDescription("aaa");
        showLocal.setGenre(genre);
        showLocal.setDuration(30);
        dao.create(showLocal);
    }

    @Test
    public void removeTest() {
        assertNotNull(show);
        dao.remove(show);

        Show showFromDb = em.find(Show.class, show.getId());
        assertNull(showFromDb);
        show = null; // Do not remove from db in cleanup
    }

    @Test
    public void findAllTest() {
        Show s1 = createShow("AAA", "aaa", genre);
        em.persist(s1);
        Show s2 = createShow("BBB", "bbb", genre);
        em.persist(s2);
        List<Show> shows = dao.findAll();
        assertEquals(3, shows.size());
        assertEquals(shows.get(0), show);
        assertEquals(shows.get(1), s1);
        assertEquals(shows.get(2), s2);

        removeShow(s1);
        removeShow(s2);
    }

    @Test
    public void findByIdTest() {
        Show showFromDb = dao.findById(show.getId());
        assertNotNull(showFromDb);
        assertEquals(show, showFromDb);
    }

    @Test
    public void findAllByGenreTest() {
        List<Show> showsFromDb = dao.findAllByGenre(genre);
        assertNotNull(showsFromDb);
        assertNotEquals(0, showsFromDb.size());
        assertEquals(show, showsFromDb.get(0));
    }

    @Test
    public void updateTest() {
        Show showFromDb = em.find(Show.class, show.getId());
        assertEquals(show, showFromDb);

        show.setName("Amet");
        assertEquals("Amet", show.getName());
        dao.update(show);

        Show showFromDbAfterUpdate = em.find(Show.class, show.getId());
        assertEquals(show, showFromDbAfterUpdate);
        assertEquals("Amet", showFromDbAfterUpdate.getName());
    }
}
