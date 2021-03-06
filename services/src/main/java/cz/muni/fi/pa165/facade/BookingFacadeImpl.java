package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BookingDTO;
import cz.muni.fi.pa165.dto.CreateBookingDTO;
import cz.muni.fi.pa165.dto.PayBookingDTO;
import cz.muni.fi.pa165.dto.TicketDTO;
import cz.muni.fi.pa165.entity.Booking;
import cz.muni.fi.pa165.entity.Ticket;
import cz.muni.fi.pa165.entity.Users;
import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.services.BookingService;
import cz.muni.fi.pa165.services.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookingFacadeImpl implements BookingFacade{

    @Inject
    BookingService bookingService;
    
    @Inject
    UserService userService;

    @Inject
    BeanMappingService beanMappingService;

    @Override
    public List<BookingDTO> getAll() {
        return beanMappingService.mapTo(bookingService.getAll(), BookingDTO.class);
    }
    
    @Override
    public List<BookingDTO> getAllByUser(Long id) {
        Users user = userService.findById(id);
        return beanMappingService.mapTo(bookingService.getAllByUser(user), BookingDTO.class);
    }

    @Override
    public BookingDTO getById(Long id) {
        return beanMappingService.mapTo(bookingService.getById(id), BookingDTO.class);
    }

    @Override
    public Long create(CreateBookingDTO dto) {
        Booking booking = beanMappingService.mapTo(dto, Booking.class);
        return bookingService.create(booking);
    }

    @Override
    public TicketDTO pay(PayBookingDTO dto) {
        Booking booking = bookingService.getById(dto.getId());
        Ticket ticket = bookingService.pay(booking);
        return beanMappingService.mapTo(ticket, TicketDTO.class);
    }

    @Override
    public boolean remove(Long id) {
        return bookingService.remove(id);
    }
}
