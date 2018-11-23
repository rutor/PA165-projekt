package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dao.ShowDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;
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
public class ShowServiceTest extends AbstractTestNGSpringContextTests {
@Mock
private ShowDao dao;
        @Inject
        @InjectMocks
    private ShowService service;

        private Genre opera;
        private Genre comedy;
        private Show show1;
        private Show show2;
        
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
    
    
    
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	opera = createGenre(1l, "Opera", "Vážná");
    	comedy = createGenre(2l, "Komedie", "Ne úplnì vážná");
    	show1 = createShow(1l, "Cesta na severní pól", "Neznáte?", 175, comedy);
    	show2 = createShow(2l, "Rusalka", "Celkem známá", 265, opera);
    }

    @Test
public void testFindById() {
		when(dao.findById(1l)).thenReturn(show1);
	assertEquals(service.findById(1l), show1);
}

    @Test
    public void testFindAll() {
    	    	when(dao.findAll()).thenReturn(Arrays.asList(show1, show2));
    	List<Show> result = service.findAll();
    	assertEquals(result.size(), 2);
    	assertEquals(show1, result.get(0));
    	assertEquals(show2, result.get(1));
    }
}
