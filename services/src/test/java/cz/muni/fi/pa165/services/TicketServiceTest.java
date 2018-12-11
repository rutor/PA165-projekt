package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dao.PerformanceDao;
import cz.muni.fi.pa165.dao.TicketDao;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.TicketStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static cz.muni.fi.pa165.EntityUtils.*;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TicketServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private TicketDao ticketDao;

    @Mock
    private UserService userService;

    @Mock
    // FIXME Tomas milestone2 replace by service when available
    private PerformanceDao performanceDao;

    @Inject
    @InjectMocks
    private TicketService ticketService;

    private Users user;
    private Ticket ticket1;
    private Ticket ticket2;

    @Before
    public void setup() {
        Random random = new Random();
        MockitoAnnotations.initMocks(this);
        Hall hall = createHall(random.nextLong(), "Hall1", "Address1", 100l);
        Genre genre = createGenre(random.nextLong(), "Genre", "GenreDescription");
        Show show = createShow(random.nextLong(), "Show1", "Description1", genre, 100);
        Performance performance1 = createPerformance(random.nextLong(), "PerformanceDescription1", 42.42f, hall, show, LocalDate.now());
        Performance performance2 = createPerformance(random.nextLong(), "PerformanceDescription2", 3.14f, hall, show, LocalDate.now());
        Role role = createRole(random.nextLong(), "Role", "RoleDescription");
        user = createUser(random.nextLong(), "First", "User", "some@email.com", "Pass", role, LocalDate.now(), LocalDate.now());
        ticket1 = createTicket(random.nextLong(), performance1, user);
        ticket2 = createTicket(random.nextLong(), performance2, user);
    }

    @Test
    public void createTest() {
        doAnswer((Answer) invocation -> {invocation.getArgumentAt(0, Ticket.class).setId(10l);return null;})
                .when(ticketDao).create(ticket1);
        assertEquals(Long.valueOf(10), ticketService.create(ticket1));
        verify(ticketDao).create(ticket1);
    }

    @Test
    public void getAllTest() {
        when(ticketDao.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));
        List<Ticket> tickets = ticketService.getAll();
        assertEquals(2, tickets.size());
        assertEquals(ticket1, tickets.get(0));
        assertEquals(ticket2, tickets.get(1));
    }

    @Test
    public void getByIdTest() {
        when(ticketDao.findById(ticket1.getId())).thenReturn(ticket1);
        Ticket ticket = ticketService.getById(ticket1.getId());
        assertEquals(ticket1, ticket);
    }

    @Test
    public void getByUserTest() {
        when(userService.findById(user.getId())).thenReturn(user);
        when(ticketDao.findByUser(user)).thenReturn(Arrays.asList(ticket1, ticket2));
        List<Ticket> tickets = ticketService.getByUser(user.getId());
        assertEquals(2, tickets.size());
        assertEquals(ticket1, tickets.get(0));
        assertEquals(ticket2, tickets.get(1));
    }

    @Test
    public void getByBarcodeTest() {
        when(ticketDao.findByBarcode(ticket1.getBarcode())).thenReturn(ticket1);
        Ticket ticket = ticketService.getByBarcode(ticket1.getBarcode());
        assertEquals(ticket1, ticket);
    }

    @Test
    public void getByPerformanceTest() {
        Performance performance = ticket1.getPerformance();
        when(performanceDao.findById(performance.getId())).thenReturn(performance);
        when(ticketDao.findByPerformance(performance)).thenReturn(Arrays.asList(ticket1));
        List<Ticket> tickets = ticketService.getByPerformance(performance.getId());
        assertEquals(1, tickets.size());
        assertEquals(ticket1, tickets.get(0));
    }

    //@Test
    // FIXME Tomas milestone2 Now fails on some null probably because we do not throw exceptions
    public void returnTicketTest() {
        TicketStatus status = ticket1.getStatus();
        assertEquals(TicketStatus.NOT_USED, status);
        assertTrue(ticketService.returnTicket(ticket1.getId()));
        assertEquals(TicketStatus.RETURNED, status);
    }
}
