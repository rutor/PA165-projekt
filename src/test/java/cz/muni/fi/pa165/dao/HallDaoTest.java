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

/**
 *
 * @author Robo
 */
//public class HallDaoTest{}


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HallDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private HallDao dao;


    //@Before
    @Test
    public void createTest() {
        Hall hall= new Hall();
        hall.setAddress("Tajovskeho");
        hall.setCapacity(400L);
        hall.setName("Velka_sala");
        
        em.persist(hall);

        Hall hall1= new Hall();
        hall1.setAddress("Sladkovicova");
        hall1.setCapacity(200L);
        hall1.setName("Mala_sala");
        
        em.persist(hall1);
        
        dao.create(hall);
        
        
        Hall hallFromDatabase = em.find(Hall.class,hall.getId());
        assertEquals(hall, hallFromDatabase);
    }




}
