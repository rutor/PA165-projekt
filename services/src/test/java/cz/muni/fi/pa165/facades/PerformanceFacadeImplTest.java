package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.entity.Performance;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.services.PerformanceService;
import cz.muni.fi.pa165.services.GenreService;
import cz.muni.fi.pa165.services.ShowService;
import cz.muni.fi.pa165.services.HallService;
import cz.muni.fi.pa165.services.TestUtils;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
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
public class PerformanceFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PerformanceFacade facade;
    @Inject
    private PerformanceService service;

    @Inject
    private GenreService genreService;
    @Inject
    private ShowService showService;
    @Inject
    private HallService hallService;

    private Show show1;
    private Genre genre1;
    private Hall hall1;

    @Before
    public void setup() {
        genre1 = TestUtils.createGenre("Opera", "Vážná");
        genre1.setId(null);
        genreService.create(genre1);
        show1 = TestUtils.createShow("Cesta na severní pól", "Neznáte?", genre1, 175);
        show1.setId(null);
        showService.create(show1);
        hall1 = TestUtils.createHall("Kino pod kopcem", "Pod kopcem 25", 200L);
        hall1.setId(null);
        hallService.create(hall1);
    }

    @Test
    public void testFindById() {
        Performance performance1 = TestUtils.createPerformance("První pøedstavení", 79.9f, hall1, show1, LocalDate.ofYearDay(2019, 1));
        performance1.setId(null);
        service.create(performance1);
        PerformanceDTO performanceFromDb = facade.getPerformanceById(performance1.getId());
        assertDTOAndEntityEquals(performanceFromDb, performance1);
    }

    @Test
    public void testGetAll() {
        LocalDate date = LocalDate.now();
        Performance performance1 = TestUtils.createPerformance("První pøedstavení", 79.9f, hall1, show1, date);
        performance1.setId(null);
        service.create(performance1);

        LocalDate date2 = LocalDate.now();
        Performance performance2 = TestUtils.createPerformance("Druhé pøedstavení", 54.4f, hall1, show1, date2);
        performance2.setId(null);
        service.create(performance2);

        List<PerformanceDTO> performances = facade.getAllPerformances();
        assertEquals(2, performances.size());
        assertDTOAndEntityEquals(performances.get(0), performance1);
        assertDTOAndEntityEquals(performances.get(1), performance2);
    }

    @Test
    public void testGetAllPerfomancesByHallId() {
        LocalDate date = LocalDate.now();
        Performance performance1 = TestUtils.createPerformance("První pøedstavení", 79.9f, hall1, show1, date);
        performance1.setId(null);
        service.create(performance1);

        LocalDate date2 = LocalDate.now();
        Performance performance2 = TestUtils.createPerformance("Druhé pøedstavení", 54.4f, hall1, show1, date2);
        performance2.setId(null);
        service.create(performance2);

        List<PerformanceDTO> performances = facade.getAllPerfomancesByHallId(performance1.getHall().getId());
        assertEquals(2, performances.size());
        assertDTOAndEntityEquals(performances.get(0), performance1);
        assertDTOAndEntityEquals(performances.get(1), performance2);
    }

    @Test
    public void testRemove() {
        LocalDate date = LocalDate.now();
        Performance performance1 = TestUtils.createPerformance("První pøedstavení", 79.9f, hall1, show1, date);
        performance1.setId(null);
        service.create(performance1);

        LocalDate date2 = LocalDate.now();
        Performance performance2 = TestUtils.createPerformance("Druhé pøedstavení", 54.4f, hall1, show1, date2);
        performance2.setId(null);
        service.create(performance2);

        facade.removePerformance(performance1.getId());
        List<Performance> performances = service.findAll();
        assertEquals(performances.size(), 1);
        assertEquals(performance1, performances.get(0));
    }

    @Test
    public void testUpdate() {
        LocalDate date = LocalDate.now();
        Performance performance1 = TestUtils.createPerformance("První pøedstavení", 79.9f, hall1, show1, date);
        performance1.setId(null);
        service.create(performance1);

        PerformanceDTO performanceDTO = new PerformanceDTO();
        performanceDTO.setDescription(performance1.getDescription());
        performanceDTO.setId(performance1.getId());
        performanceDTO.setPrice(11.1f);
        facade.updatePerformance(performanceDTO);
        assertDTOAndEntityEquals(performanceDTO, service.findById(performanceDTO.getId()));
    }

    private void assertDTOAndEntityEquals(PerformanceDTO dto, Performance entity) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getHall(), dto.getHall());
        assertEquals(entity.getPrice(), dto.getPrice());
        assertEquals(entity.getShow(), dto.getShow());
        assertEquals(entity.getStartDate(), dto.getStartDate());
    }
}
