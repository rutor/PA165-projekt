package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.*;

import java.util.List;

public interface BookingFacade {

    List<BookingDTO> getAll();

    BookingDTO getById(Long id);

    Long create(CreateBookingDTO dto);

    TicketDTO pay(PayBookingDTO dto);

    boolean remove(Long id);
}
