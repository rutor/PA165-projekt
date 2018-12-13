package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.EntityUtils;
import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dto.RoleDTO;
import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.services.RoleService;
import org.junit.Before;
import org.junit.Ignore;
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
public class RoleFacadeTest extends AbstractTestNGSpringContextTests {
    @Inject
    private RoleFacade facade;
    @Inject
    private RoleService service;

    private CreateRoleDTO admin;
    private CreateRoleDTO oldCustomer;
    private CreateRoleDTO newCustomer;



    public CreateRoleDTO getCreateRoleDTO(String name, String description) {
        CreateRoleDTO createRole = new CreateRoleDTO();
        createRole.setName(name);
        createRole.setDescription(description);
        return createRole;
    }
    @Before
    public void setup() {
        admin = getCreateRoleDTO( "Admin1", "Hlavny admin");
        oldCustomer =getCreateRoleDTO("Customer", "Zakaznik");
        newCustomer = getCreateRoleDTO( "Customer", "Zakaznik dnes registrovany");

    }
    @Test @Ignore
    public void testCreateRole() {
        Long id = facade.createRole(admin);
        assertNotNull(id);
        Role adminFromDb = service.findById(id);
        assertEquals(admin.getName(), adminFromDb.getName());
        assertEquals(admin.getDescription(), adminFromDb.getDescription());

    }

    @Test @Ignore
    public void testGetById() {
        Role test = EntityUtils.createRole(null, "Admin",  "administrator");
        Long id = service.create(test);
        RoleDTO roleFromDb = facade.getRoleById(id);
        assertDTOAndEntityEquals(roleFromDb, test);
    }
    @Test @Ignore
    public void testGetAll() {
        Role testAll = EntityUtils.createRole(null, "Admin",  "administrator");
        service.create(testAll);
        Role test2 = EntityUtils.createRole(null, "Customer",  "customer");
        service.create(test2);
        List<RoleDTO> roles = facade.getAllRole();
        assertEquals(2, roles.size());
        assertDTOAndEntityEquals(roles.get(0), testAll);
        assertDTOAndEntityEquals(roles.get(1), test2);
    }
    @Test @Ignore
    public void testGetByName() {
        Role test = EntityUtils.createRole(null, "Admin",  "administrator");
        service.create(test);
        RoleDTO testFromDb = facade.getRoleByName(test.getName());
        assertDTOAndEntityEquals(testFromDb, test);
    }
    @Test @Ignore
    public void testRemove() {
        Role test1 = EntityUtils.createRole(null, "Admin",  "administrator");
        service.create(test1);
        Role test2 = EntityUtils.createRole(null, "Customer",  "old_customer");
        service.create(test2);
        facade.removeRole(test2.getId());
        List<Role> roles = service.findAll();
        assertEquals(roles.size(), 1);
        assertEquals(test1, roles.get(0));
    }
    @Test @Ignore
    public void testUpdate() {
        Role test2 = EntityUtils.createRole(null, "Customer",  "old_customer");
        service.create(test2);
        RoleDTO newTest1 = new RoleDTO();
        newTest1.setName(test2.getName());
        newTest1.setId(test2.getId());
        newTest1.setDescription("Admin");
        facade.updateRole(newTest1);
        assertDTOAndEntityEquals(newTest1, service.findById(newTest1.getId()));
    }
    private void assertDTOAndEntityEquals(RoleDTO dto, Role entity) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getDescription(), dto.getDescription());
    }

}