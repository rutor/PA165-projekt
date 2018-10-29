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
import java.util.List;

import javax.inject.Inject;
import javax.persistence.*;

import static org.junit.Assert.*;

/**
 *
 * @author Robo
 */



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HallDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private HallDao dao;

    private Hall bigHall= new Hall();
    private Hall smallHall= new Hall();


    @Before
    public void setup() {
        bigHall.setAddress("Tajovskeho");
        bigHall.setCapacity(400L);
        bigHall.setName("Velka_sala");

        em.persist(bigHall);


        smallHall.setAddress("Sladkovicova");
        smallHall.setCapacity(200L);
        smallHall.setName("Mala_sala");

        em.persist(smallHall);

      //  em.getTransaction().commit();
       // em.close();

    }

    @Test
    public void createTest() {
        dao.create(smallHall);
        Hall hallFromDatabase = em.find(Hall.class,smallHall.getId());
        assertEquals(smallHall, hallFromDatabase);
    }




    @Test
    public void removeTest() {
        em.persist(smallHall);
        assertEquals(em.find(Hall.class, smallHall.getId()), smallHall);
        em.remove(smallHall);
        Assert.assertNull(em.find(Hall.class, smallHall.getId()));


    }
/*
    @Test
    public void findAllTest() {
        Hall hall = new Hall();
        hall.setAddress("Podjavorinska");
        hall.setCapacity(300L);
        hall.setName("Stredna_sala");

        em.persist(hall);


        List<Hall> found = dao.findAll();

        Assert.assertEquals(found.size(), 2);
        for (Hall u : found) {
            if (!(u.equals(hall) ||(u.equals(bigHall))|| u.equals(smallHall))) {
                Assert.fail("Found Hall should not exists in db.");
            }
        }
    }*/
    /*
    @Test
    public void  updateTest(){}

    @Test
    public void findByIdTest(){}
*/


}
