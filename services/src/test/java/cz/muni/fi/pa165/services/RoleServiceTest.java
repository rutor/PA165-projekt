package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.dao.RoleDao;
import cz.muni.fi.pa165.entity.Role;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.ServicesContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RoleServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    RoleDao dao;
    @Inject
    @InjectMocks
    private RoleService service;

    private Role admin;
    private Role oldCustomer;
    private Role newCustomer;

    public Role createRole(Long id, String name, String description) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setDescription(description);
        return role;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        admin = createRole(1l, "Admin1", "Hlavny admin");
        oldCustomer = createRole(2l, "Customer", "Zakaznik");
        newCustomer = createRole(3l, "Customer", "Zakaznik dnes registrovany");
    }

    @Test
    public void testFindById() {
        when(dao.findById(1l)).thenReturn(admin);
        assertEquals(admin,service.findById(1l));
    }

    @Test
    public void testFindAll() {
        List<Role> roles = Arrays.asList(admin, oldCustomer, newCustomer);
        when(dao.findAll()).thenReturn(roles);
        List<Role> result = service.findAll();
        assertEquals(result.size(), 3);
        assertEquals(roles.get(0), result.get(0));
        assertEquals(roles.get(1), result.get(1));
        assertEquals(roles.get(2), result.get(2));
    }
    @Test
    public void testCreate() {
        assertEquals(service.create(admin), new Long(1));
        verify(dao).create(admin);
    }
    @Test
    public void testRemove() {
        service.remove(oldCustomer);
        verify(dao).remove(oldCustomer);
    }
    @Test
    public void testUpdate() {
        service.update(admin);
        verify(dao).update(admin);
    }

    @Test
    public void testFindByName() {
        when(dao.findByName("Admin1")).thenReturn(admin);
        assertEquals(admin,service.findByName("Admin1"));
    }

}
