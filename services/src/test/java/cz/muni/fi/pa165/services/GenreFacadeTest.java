package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.dto.CreateGenreDTO;
import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.facade.GenreFacade;

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
public class GenreFacadeTest extends AbstractTestNGSpringContextTests {
        @Inject
    private GenreFacade facade;

        private CreateGenreDTO newOpera;
        private CreateGenreDTO newComedy;
    public CreateGenreDTO getCreateGenreDTO(String name, String description) {
        CreateGenreDTO cg = new CreateGenreDTO();
        cg.setName(name);
        cg.setDescription(description);
        return cg;
    }

    @Before
    public void setup() {
    	newOpera = getCreateGenreDTO("Opera", "Však víte.");
    	newComedy = getCreateGenreDTO("Komedie", "Byla, je a bude");
    }
    @Test
    public void testCreateGenre() {
    	Long id = facade.createGenre(newOpera);
    	GenreDTO operaFromDb = facade.getGenreById(id);
    	assertEquals(newOpera.getName(), operaFromDb.getName());
    	assertEquals(newOpera.getDescription(), operaFromDb.getDescription());
    }
}
