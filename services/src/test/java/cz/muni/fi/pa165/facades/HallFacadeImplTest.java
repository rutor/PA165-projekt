package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dto.HallDTO;
import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.facade.HallFacade;
import cz.muni.fi.pa165.services.HallService;
import cz.muni.fi.pa165.services.TestUtils;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xtrnkal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HallFacadeImplTest extends AbstractTestNGSpringContextTests {
    @Inject
    private HallFacade facade;
    @Inject
    private HallService service;
    
    @Test
    public void testFindById() {
        
        Hall test = TestUtils.createHall("Opera", "Botanická 68a, Brno", 300L);
        test.setId(null);
        Long id = service.create(test);
        HallDTO hallFromDb = facade.getHallById(id);
        assertDTOAndEntityEquals(hallFromDb, test);
    }

    @Test
    public void testGetAll() {
        Hall opera = TestUtils.createHall("Opera", "Botanická 68a, Brno", 300L);
        opera.setId(null);
        service.create(opera);
        Hall cinema = TestUtils.createHall("Kino", "Nerudova 6874, Brno", 250L);
        cinema.setId(null);
        service.create(cinema);
        List<HallDTO> halls = facade.getAllHalls();
        assertEquals(2, halls.size());
        assertDTOAndEntityEquals(halls.get(0), opera);
        assertDTOAndEntityEquals(halls.get(1), cinema);
    }

    @Test
    public void testGetByName() {
        Hall opera = TestUtils.createHall("Opera", "Botanická 68a, Brno", 300L);
        opera.setId(null);
        service.create(opera);
        HallDTO testFromDb = facade.getHallByName(opera.getName());
        assertDTOAndEntityEquals(testFromDb, opera);
    }
    
    @Test
    public void testRemove() {
        Hall opera = TestUtils.createHall("Opera", "Botanická 68a, Brno", 300L);
        opera.setId(null);
        service.create(opera);
        Hall cinema = TestUtils.createHall("Kino", "Nerudova 6874, Brno", 250L);
        cinema.setId(null);
        service.create(cinema);
        facade.removeHall(cinema.getId());
        List<Hall> halls = service.findAll();
        assertEquals(halls.size(), 1);
        assertEquals(opera, halls.get(0));
    }
    
    @Test
    public void testUpdate() {
        Hall opera = TestUtils.createHall("Opera", "Botanická 68a, Brno", 300L);
        opera.setId(null);
        service.create(opera);
        HallDTO operaDTO = new HallDTO();
        operaDTO.setName(opera.getName());
        operaDTO.setId(opera.getId());
        operaDTO.setCapacity(100L);
        facade.updateHall(operaDTO);
        assertDTOAndEntityEquals(operaDTO, service.findById(operaDTO.getId()));
    }
    
    private void assertDTOAndEntityEquals(HallDTO dto, Hall entity) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getAddress(), dto.getAddress());
        assertEquals(entity.getCapacity(), dto.getCapacity());
    }
}
