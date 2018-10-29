package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

/**
 *
 * @author xtrnkal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @PersistenceContext
    private EntityManager em;
    
    private Role adminRole;
    private Role employeeRole;
    
    private User adminUser;
    
    @BeforeClass
    public void OnlyOnce() {
        EntityManager e = emf.createEntityManager();
        e.getTransaction().begin();
        
        adminRole = new Role();
        adminRole.setName("Admin");
        adminRole.setDescription("Admin can do everything.");
        e.persist(adminRole);
        
        employeeRole = new Role();
        employeeRole.setName("Employee");
        employeeRole.setDescription("Employee can use majority of the system except user and role managing.");
        e.persist(employeeRole);
        
        e.getTransaction().commit();
        e.close();
    
        adminUser = new User();
        adminUser.setEmail("admin@admin.com");
        adminUser.setFirstName("John");
        adminUser.setLastname("Doe");
        adminUser.setRole(adminRole);
        
    }

    @Inject
    private UserDao userDao;
    
    @Test
    public void createTest() {
        userDao.create(adminUser);
        
        /*
        em.persist(adminUser);
        em.getTransaction().commit();
        */
        
        /*
        UserDao dao;
        dao.create(user);
        
        User dbUser = em.find(User.class, user.getId());
        assertEquals(user, dbUser);
        */
        
        User dbUser = em.find(User.class, adminUser.getId());
        assertEquals(adminUser, dbUser);
    }
    
    
    /*also more variants */
    @Test
    public void removeTest() {
        em.persist(adminUser);
        em.getTransaction().commit();
        
        em.remove(adminUser);
        em.getTransaction().commit();
        
        
        Assert.assertNull(em.find(User.class, adminUser.getId()));
        
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
