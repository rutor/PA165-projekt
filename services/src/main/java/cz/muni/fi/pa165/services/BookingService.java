package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.entity.Booking;
import cz.muni.fi.pa165.entity.Ticket;

import java.util.List;

public interface BookingService {

    List<Booking> getAll();

    Booking getById(Long id);

    Long create(Booking booking);

    Ticket pay(Booking booking);
}
