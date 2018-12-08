package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.ApplicationContext;
import cz.muni.fi.pa165.entity.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

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
    private Users user;
        
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
    	Role role = new Role();
    	role.setName("admin");
    	em.persist(role);
    	user = new Users();
    	user.setFirstName("Petr");
    	user.setLastName("Adamek");
    	user.setEmail("adamek@adamek.org");
    	user.setPassword("sha256_hash?");
    	user.setRole(role);
em.persist(user);    
    }
    
    private Ticket getTicket() {
    	Ticket ticket = new Ticket();
    	ticket.setCreatedAt(LocalDate.now());
    	ticket.setUpdatedAt(LocalDate.now());
    	ticket.setBarcode(UUID.randomUUID());
    	ticket.setPerformance(performance);
    	ticket.setUser(user);
    	return ticket;
    }
    
    @Test
    public void testCreate() {
        ticketDao.create(getTicket());
        assertEquals(em.createQuery("select count(t) from Ticket t", Long.class).getSingleResult(), new Long(1));
    }
    
    @Test
    public void testFindAll() {
    	Ticket first = getTicket();
    	Ticket second = getTicket();
    	em.persist(first);
    	em.persist(second);
    	List<Ticket> tickets = ticketDao.findAll();
    	assertEquals(tickets.size(), 2);
    	assertEquals(tickets.get(0), first);
    	assertEquals(tickets.get(1), second);
    }

    @Test
    public void testFindById() {
    	Ticket oldTicket = getTicket();
    	em.persist(oldTicket);
    	Ticket newTicket = ticketDao.findById(oldTicket.getId());
    			assertEquals(oldTicket, newTicket);
    }

    @Test
    public void testFindByBarcode() {
    	Ticket ticket = getTicket();
    	em.persist(ticket);
    	assertEquals(ticketDao.findByBarcode(ticket.getBarcode()), ticket);    	
    }
    
    @Test
public void testFindByCreatedAt() {
	Ticket ticket = getTicket();
	em.persist(ticket);
	assertEquals(ticketDao.findByCreationDate(ticket.getCreatedAt()).get(0), ticket);
}

@Test
public void testFindByUpdatedAt() {
	Ticket ticket = getTicket();
	em.persist(ticket);
		assertEquals(ticketDao.findByUpdateDate(ticket.getUpdatedAt()).get(0), ticket);
}

@Test
public void testDelete() {
	Ticket ticket = getTicket();
	em.persist(ticket);
	ticketDao.delete(ticket);
	assertEquals(em.createQuery("select count(t) from Ticket t", Long.class).getSingleResult(), new Long(0));
}

@Test
public void testUpdate() {
	Ticket original = getTicket();
	em.persist(original);
	assertEquals(em.find(Ticket.class, original.getId()), original);
	em.clear();
	UUID newBarcode = UUID.randomUUID();
	original.setBarcode(newBarcode);
	ticketDao.update(original);
	assertEquals(em.find(Ticket.class,  original.getId()), original);
}
@Test
public void testFindByPerformance() {
	Ticket ticket = getTicket();
	em.persist(ticket);
	assertEquals(ticketDao.findByPerformance(performance).get(0), ticket);
}
@Test
public void testFindByUser() {
	Ticket ticket = getTicket();
	em.persist(ticket);
	assertEquals(ticketDao.findByUser(user).get(0), ticket);
}
@Test(expected=javax.validation.ConstraintViolationException.class)
public void testCanNotSaveWithoutUser() {
	Ticket ticket = getTicket();
	ticket.setUser(null);
	ticketDao.create(ticket);
}

}
