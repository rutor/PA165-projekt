package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.dao.BookingDao;
import cz.muni.fi.pa165.dao.TicketDao;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.PaymentStatus;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static cz.muni.fi.pa165.EntityUtils.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BookingServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private BookingDao bookingDao;

    @Mock
    private TicketDao ticketDao;

    @Inject
    @InjectMocks
    private TicketService ticketService;

    @Inject
    @InjectMocks
    private BookingService bookingService;

    private Booking booking1;
    private Booking booking2;
    private Users user;
    private Performance performance;

    @Before
    public void setup() {
        Random random = new Random();
        MockitoAnnotations.initMocks(this);
        Role role = createRole(random.nextLong(), "Role1", "RoleDescription");
        user = createUser(random.nextLong(), "FirstName", "LastName", "some@email.com", "pass", role, LocalDate.now(), LocalDate.now());
        Hall hall = createHall(random.nextLong(), "Hall1", "Address1", 100l);
        Genre genre = createGenre(random.nextLong(), "Genre1", "GenreDescription");
        Show show = createShow(random.nextLong(), "Show1", "ShowDescription", genre, 120);
        performance = createPerformance(random.nextLong(), "PerformanceDescription", 42.42f, hall, show, LocalDateTime.now());
        booking1 = createBooking(random.nextLong(), user, "description", PaymentStatus.PAYED, performance, LocalDate.now(), LocalDate.now());
        booking2 = createBooking(random.nextLong(), user, "descriptionDiffers", PaymentStatus.PAYED, performance, LocalDate.now(), LocalDate.now());
    }

    @Test
    public void getAllTest() {
        when(bookingDao.findAll()).thenReturn(Arrays.asList(booking1, booking2));
        List<Booking> bookings = bookingService.getAll();
        assertEquals(2, bookings.size());
        assertEquals(booking1, bookings.get(0));
        assertEquals(booking2, bookings.get(1));
    }

    @Test
    public void getByIdTest() {
        when(bookingDao.findById(booking1.getId())).thenReturn(booking1);
        Booking booking = bookingService.getById(booking1.getId());
        assertEquals(booking1, booking);
        assertNotEquals(booking2, booking);
    }

    @Test
    public void createTest() {
        assertEquals(booking1.getId(), bookingService.create(booking1));
        verify(bookingDao).create(booking1);
    }

    @Test
    public void payTest() {
        doAnswer((Answer) invocation -> {invocation.getArgumentAt(0, Ticket.class).setId(10l);return null;})
                .when(ticketDao).create(any(Ticket.class));
        bookingService.create(booking1);
        when(bookingDao.findById(booking1.getId())).thenReturn(booking1);
        Ticket ticket = bookingService.pay(booking1);
        assertEquals(ticket, booking1.getTicket());
        assertEquals(TicketStatus.NOT_USED, ticket.getStatus());
    }
}
