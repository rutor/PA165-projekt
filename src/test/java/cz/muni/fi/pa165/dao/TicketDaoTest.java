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
public class TicketDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private TicketDao ticketDao;
    
    @PersistenceContext
    private EntityManager em;
    
    private Show show;
    private Performance performance;
    private Genre opera;
        
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
    }
    
    private Ticket getTicket() {
    	Ticket ticket = new Ticket();
    	ticket.setCreatedAt(LocalDate.now());
    	ticket.setUpdatedAt(LocalDate.now());
    	ticket.setBarcode(UUID.randomUUID());
    	return ticket;
    }
    
    @Test
    public void testCreate() {
        ticketDao.create(getTicket());
    }
    
    @Test
    public void testFindAll() {
    	ticketDao.create(getTicket());
    	ticketDao.create(getTicket());
    	List<Ticket> tickets = ticketDao.findAll();
    	assertEquals(tickets.size(), 2);
    	assertNotEquals(tickets.get(0).getId(), tickets.get(1).getId());
    }

    @Test
    public void testFindById() {
    	Ticket oldTicket = getTicket();
    	ticketDao.create(oldTicket);
    	Ticket newTicket = ticketDao.findById(oldTicket.getId());
    			assertEquals(oldTicket, newTicket);
    }

    @Test
    public void testFindByBarcode() {
    	Ticket ticket = getTicket();
    	ticketDao.create(ticket);
    	assertNotEquals(ticketDao.findByBarcode(ticket.getBarcode()), null);    	
    }
    
    @Test
public void testFindByCreatedAt() {
	Ticket ticket = getTicket();
	ticketDao.create(ticket);
	assertEquals(ticketDao.findByCreationDate(ticket.getCreatedAt()).size(), 1);
}

@Test
public void testFindByUpdatedAt() {
	Ticket ticket = getTicket();
	ticketDao.create(ticket);
		assertEquals(ticketDao.findByUpdateDate(ticket.getUpdatedAt()).size(), 1);
}

@Test
public void testDelete() {
	Ticket ticket = getTicket();
	ticketDao.create(ticket);
	ticketDao.delete(ticket);
	assertEquals(ticketDao.findAll().size(), 0);
}

@Test
public void testUpdate() {
	Ticket ticket = getTicket();
	ticketDao.update(ticket);
	assertEquals(ticketDao.findAll().size(), 1);
}
}
