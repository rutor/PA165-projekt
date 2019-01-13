package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.entity.Booking;
import cz.muni.fi.pa165.entity.Ticket;
import cz.muni.fi.pa165.entity.Users;

import java.util.List;

public interface BookingService {

    List<Booking> getAll();
    
    List<Booking> getAllByUser(Users user);

    Booking getById(Long id);

    Long create(Booking booking);

    Ticket pay(Booking booking);

    boolean remove(Long id);
}
