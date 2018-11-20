package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.*;

import java.util.List;

public interface BookingFacade {

    public List<BookingDTO> getAll();

    public BookingDTO getById(Long id);

    public void create(CreateBookingDTO dto);

    public TicketDTO pay(PayBookingDTO dto);
}
