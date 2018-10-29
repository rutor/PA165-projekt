package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xtrnkal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Inject
    //private UserDao dao;
    
    @Test
    public void createTest() {
        /*
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
        */
    }
    
    /*also more variants */
    @Test
    public void removeTest() {
    }
    
    
    /*on empty table, on table with more rows*/
    @Test
    public void findAllTest() {
    }
    
    
    /* more examples - with null value, with non existing value, with existing value*/
    @Test
    public void findByIdTest() {
    }
   
    
}
