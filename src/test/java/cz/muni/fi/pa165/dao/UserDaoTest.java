package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.*;
import java.time.LocalDate;
import java.util.UUID;
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
    private UserDao userDao;
    
    private User adminUser;
    private Role adminRole;
     
    
    @Before
    public void setup() {
        adminRole = new Role();
        adminRole.setName("Admin");
        adminRole.setDescription("Admin can do everything.");
        
        em.persist(adminRole);

        adminUser = new User();
        adminUser.setEmail("admin@admin.com");
        adminUser.setFirstName("John");
        adminUser.setLastName("Doe");
        adminUser.setPassword("ahoj");
        adminUser.setRole(adminRole);
        em.persist(adminUser);
        
    }
   
    @Test
    public void testCreate() {
        userDao.create(adminUser);
        User dbUser = em.find(User.class, adminUser.getId());
        assertEquals(adminUser, dbUser);
    }
    
    
    /*also more variants */
    /*
    @Test
    public void removeTest() {
        em.persist(adminUser);
        em.getTransaction().commit();
        
        em.remove(adminUser);
        em.getTransaction().commit();
        
        
        Assert.assertNull(em.find(User.class, adminUser.getId()));
        
    }
    */
    
    /*on empty table, on table with more rows*/
    /*
    @Test
    public void findAllTest() {
    }
    
    */
    /* more examples - with null value, with non existing value, with existing value*/
    /*
    @Test
    public void findByIdTest() {
    }
   
    */
}
