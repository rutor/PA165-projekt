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
    
    private Users adminUser;
    private Role adminRole;
     
    @Before
    public void setup() {
        adminRole = new Role();
        adminRole.setName("Admin");
        adminRole.setDescription("Admin can do everything.");
        
        em.persist(adminRole);

        adminUser = new Users();
        adminUser.setEmail("admin@admin.com");
        adminUser.setFirstName("John");
        adminUser.setLastName("Doe");
        adminUser.setPassword("12345");
        adminUser.setRole(adminRole);
        em.persist(adminUser);
    }
   
    @Test
    public void testCreate() {
        userDao.create(adminUser);
        Users dbUser = em.find(Users.class, adminUser.getId());
        assertEquals(adminUser, dbUser);
    }
    
    @Test
    public void removeTest() {
        em.persist(adminUser);
        assertEquals(em.find(Users.class, adminUser.getId()), adminUser);        
        userDao.remove(adminUser);
        Assert.assertNull(em.find(Users.class, adminUser.getId()));
    }
    
    @Test
    public void findAllTest() {
        Role role = new Role();
        role.setName("Employee");
        role.setDescription("Employee cant do everything.");
        em.persist(role);
        
        Users user = new Users();
        user.setEmail("e@e.com");
        user.setFirstName("Johny");
        user.setLastName("Doee");
        user.setPassword("abcde");
        user.setRole(role);
        em.persist(user);
        
        List<Users> found = userDao.findAll();
        
        Assert.assertEquals(found.size(), 2);
        for (Users u : found) {
            if (!(u.equals(user) || u.equals(adminUser))) {
                Assert.fail("Found user should not exists in db.");
            }
        }
    }
    
    @Test
    public void findAllOnEmptyTableTest() {
        em.remove(adminUser);
        List<Users> found = userDao.findAll();
        Assert.assertEquals(found.size(), 0);
    }
    
    @Test
    public void findByIdTest() {
        em.persist(adminUser);
        Users user = userDao.findById(adminUser.getId());
        assertEquals(adminUser, user);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void findByIdWithNullTest() {
        em.persist(adminUser);
        Assert.assertNull(userDao.findById(null));
    }
    
    @Test
    public void findByIdWithNonExistingIdTest() {
        em.persist(adminUser);
        Long fakeId = 100L;
        Assert.assertNull(userDao.findById(fakeId));
    }
    
    @Test
    public void updateTest() {
        em.persist(adminUser);
        Users u = em.find(Users.class, adminUser.getId());
        
        String name = "George";
        u.setFirstName(name);
        adminUser.setFirstName(name);
        userDao.update(adminUser);
        
        assertEquals(em.find(Users.class, adminUser.getId()), u);
    }
    
    /*
    @Test
    public void findByRoleTest() {
        em.persist(adminUser);
        List<Users> found = userDao.findByRole(adminRole);
        
        Assert.assertEquals(found.size(), 1);
        for (Users u : found) {
            if (!(u.equals(adminUser))) {
                Assert.fail("Found user should not exists in db.");
            } else {
                assertEquals(adminUser, u);
            }
        } 
    }
    */
}
