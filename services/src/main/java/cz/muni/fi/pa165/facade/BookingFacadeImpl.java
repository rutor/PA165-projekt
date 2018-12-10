package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BookingDTO;
import cz.muni.fi.pa165.dto.CreateBookingDTO;
import cz.muni.fi.pa165.dto.PayBookingDTO;
import cz.muni.fi.pa165.dto.TicketDTO;
import cz.muni.fi.pa165.entity.Booking;
import cz.muni.fi.pa165.entity.Ticket;
import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.services.BookingService;
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
    BeanMappingService beanMappingService;

    @Override
    public List<BookingDTO> getAll() {
        return beanMappingService.mapTo(bookingService.getAll(), BookingDTO.class);
    }

    @Override
    public BookingDTO getById(Long id) {
        return beanMappingService.mapTo(bookingService.getById(id), BookingDTO.class);
    }

    @Override
    public Long create(CreateBookingDTO dto) {
        /*Booking booking = new Booking();
        booking.setDescription(dto.getDescription());
        // FIXME Tomas milestone2 Add all missing setters after DTOs are implemented*/
        Booking booking = beanMappingService.mapTo(dto, Booking.class);
        return bookingService.create(booking);
    }

    @Override
    public TicketDTO pay(PayBookingDTO dto) {
        Booking booking = bookingService.getById(dto.getId());
        Ticket ticket = bookingService.pay(booking);
        return beanMappingService.mapTo(ticket, TicketDTO.class);
    }
}
