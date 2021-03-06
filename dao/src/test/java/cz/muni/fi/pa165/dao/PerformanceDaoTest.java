package cz.muni.fi.pa165.dao;



import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.*;

import static org.junit.Assert.*;

/**
 *
 * @author Robo
 */
//public class PerformanceDaoTest{}


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PerformanceDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PerformanceDao dao;

    private Hall bigHall= new Hall();
    private Hall smallHall= new Hall();
    private Genre genre = new Genre();
    private Performance performance= new Performance();
    private Show show= new Show();

    @Before
    public void setup() {
        bigHall.setAddress("Tajovskeho");
        bigHall.setCapacity(400L);
        bigHall.setName("Velka_sala");

        em.persist(bigHall);

        genre.setName("Horror");
        genre.setDescription("best");

        em.persist(genre);

        show.setGenre(genre);
        show.setName("The_Strangers_2");
        show.setDescription("best");
        show.setGenre(genre);
        show.setDuration(140);
        show.setName("The_Strangers_2");

        em.persist(show);

        performance.setHall(bigHall);
        performance.setPrice(250f);
        performance.setStartDate(LocalDateTime.now());

      //  em.getTransaction().commit();
      //  em.close();
    }

    @Test
    public void createTest() {

       dao.create(performance);
       Performance performanceFromDatabase = em.find(Performance.class, performance.getId());
        assertEquals(performance, performanceFromDatabase);
    }
    @Test
    public void removeTest() {
        em.persist(performance);
        assertEquals(em.find(Performance.class, performance.getId()), performance);
        em.remove(performance);
        Assert.assertNull(em.find(Performance.class, performance.getId()));
    }


    @Test
    public void findAllTest() {
        Hall hall = new Hall();
        hall.setAddress("Podjavorinska");
        hall.setCapacity(300L);
        hall.setName("Stredna_sala");

        em.persist(hall);

        Genre genre = new Genre();
        genre.setName("Drama");
        genre.setDescription("Good");

        em.persist(genre);

        Show show= new Show();
        show.setGenre(genre);
        show.setName("The_Strangers_3");
        show.setDescription("best");
        show.setGenre(genre);
        show.setDuration(140);
        show.setName("The_Strangers_3");

        em.persist(show);

        Performance performance1= new Performance();
        performance1.setHall(bigHall);
        performance1.setPrice(250f);
        performance1.setStartDate(LocalDateTime.now());


        em.persist(performance1);

        List<Performance> found = dao.findAll();

        Assert.assertEquals(found.size(), 1);
        for (Performance u : found) {
            if (!(u.equals(performance1) || u.equals(performance))) {
                Assert.fail("Found performance should not exists in db.");
            }
        }
    }

    @Test
    public void findAllOnEmptyTableTest() {
        em.remove(performance);
        List<Performance> found = dao.findAll();
        Assert.assertEquals(found.size(), 0);
    }

    @Test
    public void findByIdTest() {
        em.persist(performance);
        Performance performance1 = dao.findById(performance.getId());
        assertEquals(performance, performance1);

    }



    @Test(expected = DataAccessException.class)
    public void findByIdWithNullTest() {
        em.persist(performance);
        Assert.assertNull(dao.findById(null));
    }

    @Test
    public void findByIdWithNonExistingIdTest() {
        em.persist(performance);
        Long fakeId = 100L;
        Assert.assertNull(dao.findById(fakeId));
    }

    /*
    @Test
    public void  updateTest(){}

    @Test
    public void findByIdTest(){}*/
}
