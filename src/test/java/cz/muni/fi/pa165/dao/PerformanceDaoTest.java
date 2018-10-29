package cz.muni.fi.pa165.dao;



import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.*;
import java.time.LocalDate;
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

    @Test
    public void createTest() {

        Hall hall =new Hall();
        
        hall.setAddress("Tajovskeho");
        hall.setCapacity(400L);
        hall.setName("Velka_sala");
        
        em.persist(hall);
       
        Genre genre = new Genre();
        genre.setName("Horror");
        genre.setDescription("best");
        
        em.persist(genre);
           
        Show show= new Show();
        show.setGenre(genre);
        show.setName("The_Strangers_2");
        show.setDescription("best");
        show.setGenre(genre);
        show.setDuration(140);
        show.setName("The_Strangers_2");
        
        em.persist(show);        
        
        Performance performance= new Performance();
        performance.setHall(hall);
        performance.setPrice(250f);
        performance.setStartDate(LocalDate.now());
       
        //em.persist(performance);

        dao.create(performance);

        Performance performanceFromDatabase = em.find(Performance.class, performance.getId());
        assertEquals(performance, performanceFromDatabase);
    }

}
