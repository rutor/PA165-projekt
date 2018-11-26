package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.entity.Users;

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
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private UserDao dao;
    @Inject
    @InjectMocks
    private UserService service;

    private Role admin;
    private Role old_customer;
    private Role new_customer;
    private Users user_admin;
    private Users user_old_customer;
    private Users user_new_customer;

    public Role createRole(Long id, String name, String description) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setDescription(description);
        return role;
    }

    private Users createUser(Long id, String firstName, String lastName,String email, String password, LocalDate createdAt, LocalDate updatedAt,Role role) {
        Users user = new Users();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        user.setRole(role);
        return user;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        admin = createRole(1l, "Admin1", "Hlavny admin");
        old_customer = createRole(2l, "Customer", "Zakaznik");
        new_customer = createRole(3l, "Customer", "Zakaznik dnes registrovany");

        user_admin = createUser(1l,"Tomas","Rudolf","tomas@gmail.com","admin123",LocalDate.now(),LocalDate.now(),admin);
        user_old_customer =createUser(2l,"Robert","Dudas","robo@gmail.com","robo123",LocalDate.now().minusYears( 5 ),LocalDate.now(),old_customer);
        user_new_customer = createUser(3l,"Lukas","Tyrychtr","lukas@gmail.com","lukas123",LocalDate.now().minusDays( 1 ),LocalDate.now(),new_customer);


    }

    @Test
    public void testFindById() {
        when(dao.findById(1l)).thenReturn(user_admin);
        assertEquals(service.findById(1l), user_admin);
    }

    @Test
    public void testFindAll() {
        when(dao.findAll()).thenReturn(Arrays.asList(user_admin,  user_old_customer,user_new_customer));
        List<Users> result = service.findAll();
        assertEquals(result.size(), 3);
        assertEquals(user_admin, result.get(0));
        assertEquals(user_old_customer, result.get(1));
        assertEquals(user_new_customer, result.get(2));
    }

    @Test
    public void testCreate() {
        assertEquals(service.create(user_admin), new Long(1));
        verify(dao).create(user_admin);
    }

    @Test
    public void testRemove() {
        service.remove(user_old_customer);
        verify(dao).remove(user_old_customer);
    }

    @Test
    public void testUpdate() {
        service.update(user_new_customer);
        verify(dao).update(user_new_customer);
    }


    @Test
    public void findByName() {
       when(dao.findByName("Rudolf")).thenReturn(user_admin);
        assertEquals(service.findByName("Rudolf"), user_admin);
    }

    @Test
    public void findByEmail() {
        when(dao.findByEmail("tomas@gmail.com")).thenReturn(user_admin);
        assertEquals(service.findByEmail("tomas@gmail.com"), user_admin);

    }
}