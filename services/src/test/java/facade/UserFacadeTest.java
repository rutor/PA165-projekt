package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CreateUserDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.RoleDTO;
import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.entity.Users;
import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.services.RoleService;
import cz.muni.fi.pa165.services.UserService;
import cz.muni.fi.pa165.services.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import java.util.List;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserFacadeTest extends AbstractTestNGSpringContextTests {
    @Inject
    private UserFacade facade;
    @Inject
    private RoleService roleService;
    @Inject
    private UserService userService;


    @Test
    public void testCreate() {
        Role role = TestUtils.createRole("Admin",  "administrator");
        role.setId(null);
        roleService.create(role);
        CreateUserDTO user = new CreateUserDTO();
        user.setFirstName("Robo");
        user.setLastName("Dudas");
        user.setPassword("robo123");
        user.setEmail("robo@robo.com");
        user.setRoleId(role.getId());
        Long id = facade.createUser(user);
        assertNotNull(id);
        Users userFromDb = userService.findById(id);
        assertEquals(user.getLastName(), userFromDb.getLastName());
    }
    @Test
    public void testGetById() {

        Users test = newUser("Dudas", "Robert","robo@robo.com","robo123","Customer","new_custome");
        UserDTO testFromDb = facade.getUserById(test.getId());
        assertDTOAndEntityEquals(testFromDb, test);
    }

    @Test
    public void testGetAll() {
        Users test = newUser("Dudas", "Robert","robo@robo.com","robo123","Customer","new_custome");
        Users test2 = newUser("Rudolf", "Tomas","tomas@rudolf.com","rudolf123","Admin","new_custome");
        List<UserDTO> shows = facade.getAllUser();
        assertEquals(2, shows.size());
        assertDTOAndEntityEquals(shows.get(0), test);
        assertDTOAndEntityEquals(shows.get(1), test2);
    }

    @Test
    public void testRemove() {
        Users test = newUser("Dudas", "Robert","robo@robo.com","robo123","Customer","new_custome");
        Users test2 = newUser("Rudolf", "Tomas","tomas@rudolf.com","rudolf123","Admin","new_custome");
        facade.removeUser(test2.getId());
        List<Users> users = userService.findAll();
        assertEquals(users.size(), 1);
        assertEquals(test, users.get(0));
    }
    /*
    @Test
    public void testUpdate() {
        Users test = newUser("Dudas", "Robert","robo@robo.com","robo123","Customer","new_custome");
        RoleDTO role = new RoleDTO();
        UserDTO s = new UserDTO();
        role.setName(test.getRole().getName());
        role.setId(test.getRole().getId());
        role.setDescription(test.getRole().getDescription());
        s.setLastName(test.getLastName());
        s.setId(test.getId());
        s.setRole(role);
        facade.updateUser(s);
        assertDTOAndEntityEquals(s, userService.findById(s.getId()));
    }*/
    private void assertDTOAndEntityEquals(RoleDTO dto, Role entity) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getDescription(), dto.getDescription());
    }
    private void assertDTOAndEntityEquals(UserDTO dto, Users entity) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getLastName(), dto.getLastName());
        assertEquals(entity.getEmail(), dto.getEmail());
        assertDTOAndEntityEquals(dto.getRole(), entity.getRole());
    }
    private Users newUser(String LastName, String FirstName,String Email,String Password, String roleName, String description) {
        Role  role = new Role();
        role.setName(roleName);
        role.setDescription(description);
        roleService.create(role);
        Users user = new Users();
        user.setRole(role);
        user.setLastName(LastName);
        user.setFirstName(FirstName);
        user.setEmail(Email);
        user.setPassword(Password);
        userService.create(user);
        return user;
    }
}