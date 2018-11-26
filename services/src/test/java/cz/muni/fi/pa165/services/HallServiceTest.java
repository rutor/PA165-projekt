package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dao.HallDao;
import cz.muni.fi.pa165.entity.Hall;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;
import static org.mockito.Mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 *
 * @author xtrnkal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HallServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    HallDao dao;
    @Inject
    @InjectMocks
    private HallService service;

    private Hall opera;
    private Hall cinema;

    public Hall createHall(Long id, String name, Long capacity) {
        Hall h = new Hall();
        h.setId(id);
        h.setName(name);
        h.setCapacity(capacity);
        return h;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        opera = createHall(101L, "Opera", 300L);
        cinema = createHall(102L, "Cinema", 150L);
    }

    @Test
    public void testFindById() {
        when(dao.findById(101L)).thenReturn(opera);
        assertEquals(service.findById(101L), opera);
    }

    @Test
    public void testFindAll() {
        List<Hall> halls = Arrays.asList(opera, cinema);
        when(dao.findAll()).thenReturn(halls);
        List<Hall> result = service.findAll();
        assertEquals(result.size(), 2);
        assertEquals(halls.get(0), result.get(0));
        assertEquals(halls.get(1), result.get(1));
    }

    @Test
    public void testCreate() {
        assertEquals(service.create(opera), new Long(101));
        verify(dao).create(opera);
    }

    @Test
    public void testRemove() {
        service.remove(cinema);
        verify(dao).remove(cinema);
    }

    @Test
    public void testUpdate() {
        service.update(opera);
        verify(dao).update(opera);
    }
}
