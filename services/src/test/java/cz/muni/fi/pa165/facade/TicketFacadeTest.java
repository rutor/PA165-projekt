package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.TicketDTO;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.services.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FacadesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TicketFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    private TicketService ticketService;

    @Inject
    private TicketFacade ticketFacade;

    private Ticket ticket1;
    private Ticket ticket2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ticket1 = new Ticket();
        ticket2 = new Ticket();
    }

    @Test
    public void getAllTest() {
        when(ticketService.getAll()).thenReturn(Arrays.asList(ticket1, ticket2));
        assertEquals(2, ticketService.getAll().size());
        List<TicketDTO> ticketDTOs = ticketFacade.getAll();
        assertEquals(2, ticketDTOs.size());
    }
}
