package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.PaymentStatus;

import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.persistence.*;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BookingDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private BookingDao bookingDao;
    
    @PersistenceContext
    private EntityManager em;
    
    private Show show;
    private Performance performance;
    private Genre opera;
    private Ticket ticket;
    
    @Before
    public void setup() {
    	opera = new Genre();
    	opera.setName("Opera");
    	opera.setDescription("Kdo nemá rád operu?");
    	em.persist(opera);
    	show = new Show();
    	show.setName("Hubièka");
    	show.setDescription("Smetana, však víte.");
    	show.setDuration(217);
    	show.setGenre(opera);
    	em.persist(show);
    	performance = new Performance();
    	performance.setShow(show);
    	performance.setStartDate(LocalDate.now());
    	em.persist(performance);
    	ticket = new Ticket();
    	ticket.setCreatedAt(LocalDate.now());
    	ticket.setUpdatedAt(LocalDate.now());
    	ticket.setBarcode(UUID.randomUUID());
    	em.persist(ticket);
    }
    
    private Booking getBooking() {
    	Booking booking = new Booking();
    	booking.setPaymentStatus(PaymentStatus.PAYED);
    	booking.setCreatedAt(LocalDate.now());
    	booking.setUpdatedAt(LocalDate.now());
    	return booking;
    }
    
    @Test
    public void testCreate() {
        bookingDao.create(getBooking());
    }
    
    @Test
    public void testFindAll() {
    	bookingDao.create(getBooking());
    	bookingDao.create(getBooking());
    	List<Booking> bookings = bookingDao.findAll();
    	assertEquals(bookings.size(), 2);
    	assertNotEquals(bookings.get(0).getId(), bookings.get(1).getId());
    }

    @Test
    public void testFindById() {
    	Booking oldBooking = getBooking();
    	bookingDao.create(oldBooking);
    	Booking newBooking = bookingDao.findById(oldBooking.getId());
    			assertEquals(oldBooking, newBooking);
    }

    @Test
    public void testFindByPaymentStatus() {
    	bookingDao.create(getBooking());
    	assertEquals(bookingDao.findByPaymentStatus(PaymentStatus.PAYED).size(), 1);
    	assertEquals(bookingDao.findByPaymentStatus(PaymentStatus.PROCESSING).size(), 0);
    }
@Test
public void testFindByCreatedAt() {
	Booking booking = getBooking();
	bookingDao.create(booking);;
	assertEquals(bookingDao.findByCreationDate(booking.getCreatedAt()).size(), 1);
}

@Test
public void testFindByUpdatedAt() {
	Booking booking = getBooking();
	bookingDao.create(booking);;
	assertEquals(bookingDao.findByUpdateDate(booking.getUpdatedAt()).size(), 1);
}

@Test
public void testDelete() {
	Booking booking = getBooking();
	bookingDao.create(booking);
	bookingDao.delete(booking);
	assertEquals(bookingDao.findAll().size(), 0);
}

@Test
public void testUpdate() {
	Booking booking = getBooking();
	bookingDao.update(booking);
	assertEquals(bookingDao.findAll().size(), 1);
}
}
