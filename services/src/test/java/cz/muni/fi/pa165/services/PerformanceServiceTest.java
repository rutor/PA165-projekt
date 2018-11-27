package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dao.PerformanceDao;
import cz.muni.fi.pa165.dao.ShowDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.entity.Performance;
import cz.muni.fi.pa165.entity.Show;
import java.time.LocalDate;
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

import static org.junit.Assert.*;

/**
 *
 * @author xtrnkal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PerformanceServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PerformanceDao dao;
    @Inject
    @InjectMocks
    private PerformanceService service;

    private Hall theatre;
    private Hall cinema;
    private Genre opera;
    private Genre comedy;
    private Show show1;
    private Show show2;
    private Performance performance1;
    private Performance performance2;

    public Hall createHall(Long id, String name, Long capacity) {
        Hall h = new Hall();
        h.setId(id);
        h.setName(name);
        h.setCapacity(capacity);
        return h;
    }

    public Genre createGenre(Long id, String name, String description) {
        Genre g = new Genre();
        g.setId(id);
        g.setName(name);
        g.setDescription(description);
        return g;
    }

    private Show createShow(Long id, String name, String description, int duration, Genre genre) {
        Show show = new Show();
        show.setId(id);
        show.setName(name);
        show.setDescription(description);
        show.setDuration(duration);
        show.setGenre(genre);
        return show;
    }

    private Performance createPerformance(Long id, String description, Show show, Hall hall, LocalDate startDate, Float price) {
        Performance p = new Performance();
        p.setId(id);
        p.setPrice(price);
        p.setDescription(description);
        p.setStartDate(startDate);
        p.setHall(hall);
        p.setShow(show);
        return p;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        opera = createGenre(1L, "Opera", "Vážná");
        comedy = createGenre(2L, "Komedie", "Ne úplnì vážná");
        show1 = createShow(1L, "Cesta na severní pól", "Neznáte?", 175, comedy);
        show2 = createShow(2L, "Rusalka", "Celkem známá", 265, opera);
        theatre = createHall(1L, "Divadlo na kopeèku", 350L);
        cinema = createHall(2L, "Kino pod kopcem", 200L);
        performance1 = createPerformance(10L, "První pøedstavení", show1, theatre, LocalDate.now(), 79.9f);
        performance2 = createPerformance(11L, "Cesta za pokladem", show2, cinema, LocalDate.now(), 45.1f);
    }

    @Test
    public void testFindById() {
        when(dao.findById(10L)).thenReturn(performance1);
        assertEquals(service.findById(10L), performance1);
    }

    @Test
    public void testFindAll() {
        when(dao.findAll()).thenReturn(Arrays.asList(performance1, performance2));
        List<Performance> result = service.findAll();
        assertEquals(result.size(), 2);
        assertEquals(performance1, result.get(0));
        assertEquals(performance2, result.get(1));
    }

    @Test
    public void testCreate() {
        assertEquals(service.create(performance1), new Long(10L));
        verify(dao).create(performance1);
    }

    @Test
    public void testRemove() {
        service.remove(performance1);
        verify(dao).remove(performance1);
    }

    @Test
    public void testUpdate() {
        performance2.setHall(theatre);
        performance2.setDescription("nic zajimaveho");
        service.update(performance2);
        verify(dao).update(performance2);
    }

    @Test
    public void findAllByHall() {
        when(dao.findAllByHall(theatre)).thenReturn(Collections.singletonList(performance1));
        List<Performance> performances = service.findAllByHall(theatre);
        assertEquals(performances.size(), 1);
        assertEquals(performances.get(0), performance1);
    }
    
    @Test
    public void findAllByHallNull() {
        when(dao.findAllByHall(null)).thenReturn(Collections.EMPTY_LIST);
        assertEquals(dao.findAllByHall(null).size(), 0);
    }
}
