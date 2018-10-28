package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.*;

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

    @Test
    public void createTest() {
        Genre genre = new Genre();
        genre.setName("Dolor");
        genre.setDescription("Sit");

        em.persist(genre);

        Show show = new Show();
        show.setName("Lorem");
        show.setDescription("Ipsum");
        show.setGenre(genre);
        dao.create(show);

        Show showFromDatabase = em.find(Show.class, show.getId());
        assertEquals(show, showFromDatabase);
    }
}
