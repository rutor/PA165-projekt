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
    BookingDao bookingDao;

    @Inject
    TicketDao ticketDao;

    @Override
    public List<Booking> getAll() {
        return bookingDao.findAll();
    }

    @Override
    public Booking getById(Long id) {
        return bookingDao.findById(id);
    }

    @Override
    public void create(Booking booking) {
        bookingDao.create(booking);
    }

    @Override
    public Ticket pay(Booking booking) {
        Ticket ticket = Ticket.createFromBooking(booking);
        booking.setPaymentStatus(PaymentStatus.PAYED);
        bookingDao.update(booking);
        ticketDao.create(ticket);
        return ticket;
    }
}
