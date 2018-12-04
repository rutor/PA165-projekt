package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.dao.BookingDao;
import cz.muni.fi.pa165.dao.TicketDao;
import cz.muni.fi.pa165.entity.Booking;
import cz.muni.fi.pa165.entity.Ticket;
import cz.muni.fi.pa165.enums.PaymentStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    @Inject
    private BookingDao bookingDao;

    @Inject
    private TicketService ticketService;

    @Override
    public List<Booking> getAll() {
        return bookingDao.findAll();
    }

    @Override
    public Booking getById(Long id) {
        return bookingDao.findById(id);
    }

    @Override
    public Long create(Booking booking) {
        bookingDao.create(booking);
        return booking.getId();
    }

    @Override
    public Ticket pay(Booking booking) {
        Ticket ticket = Ticket.createFromBooking(booking);
        ticketService.create(ticket);
        booking.setPaymentStatus(PaymentStatus.PAYED);
        booking.setTicket(ticket);
        bookingDao.update(booking);
        return ticket;
    }
}
