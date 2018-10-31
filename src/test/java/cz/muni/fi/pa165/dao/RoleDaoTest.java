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
public class RoleDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private RoleDao roleDao;
    
    private Role adminRole;
     
    @Before
    public void setup() {
        adminRole = new Role();
        adminRole.setName("Admin");
        adminRole.setDescription("Admin can do everything.");
        
        em.persist(adminRole);
    }
   
    @Test
    public void testCreate() {
        roleDao.create(adminRole);
        Role dbRole = em.find(Role.class, adminRole.getId());
        assertEquals(adminRole, dbRole);
    }
    
    @Test
    public void removeTest() {
        em.persist(adminRole);
        assertEquals(em.find(Role.class, adminRole.getId()), adminRole);  
        roleDao.remove(adminRole);
        Assert.assertNull(em.find(Role.class, adminRole.getId()));
    }
    
    @Test
    public void updateTest() {
        em.persist(adminRole);
        Role r = em.find(Role.class, adminRole.getId());
        
        String name = "George";
        r.setName(name);
        adminRole.setName(name);
        roleDao.update(adminRole);
        
        assertEquals(em.find(Role.class, adminRole.getId()), r);
    }
    
    @Test
    public void findAllTest() {
        Role role = new Role();
        role.setName("Employee");
        role.setDescription("Employee cant do everything.");
        em.persist(role);

        List<Role> found = roleDao.findAll();
        
        Assert.assertEquals(found.size(), 2);
        for (Role r: found) {
            if (!(r.equals(role) || r.equals(adminRole))) {
                Assert.fail("Found role should not exists in db.");
            }
        }
    }
    
    @Test
    public void findAllOnEmptyTableTest() {
        em.remove(adminRole);
        List<Role> found = roleDao.findAll();
        Assert.assertEquals(found.size(), 0);
    }
    
    @Test
    public void findByIdTest() {
        em.persist(adminRole);
        Role role = roleDao.findById(adminRole.getId());
        assertEquals(adminRole, role);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void findByIdWithNullTest() {
        em.persist(adminRole);
        Assert.assertNull(roleDao.findById(null));
    }
    
    @Test
    public void findByIdWithNonExistingIdTest() {
        em.persist(adminRole);
        Long fakeId = 100L;
        Assert.assertNull(roleDao.findById(fakeId));
    }
}
