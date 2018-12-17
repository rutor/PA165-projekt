package cz.muni.fi.pa165.services;

import cz.muni.fi.pa165.entity.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    Long create(Ticket ticket);

    List<Ticket> getAll();

    Ticket getById(Long id);

    List<Ticket> getByUser(Long id);

    Ticket getByBarcode(UUID barcode);

    List<Ticket> getByPerformance(Long id);

    boolean returnTicket(Long id);

    boolean useTicket(UUID barcode);
}
