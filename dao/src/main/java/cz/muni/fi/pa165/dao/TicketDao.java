package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Interface of DAO for Ticket entity
 * @author Tomas Rudolf
 */
public interface TicketDao {
    public void create(Ticket c);
    public Ticket findById(Long id);
    public List<Ticket> findAll();
    public Ticket findByBarcode(UUID barcode);
    public List<Ticket> findByCreationDate(LocalDate creationDate);
    public List<Ticket> findByUpdateDate(LocalDate updateDate);
    public List<Ticket> findByPerformance(Performance performance);
    public List<Ticket> findByUser(Users user);
    public void update(Ticket c);
    public void delete(Ticket c);
}
