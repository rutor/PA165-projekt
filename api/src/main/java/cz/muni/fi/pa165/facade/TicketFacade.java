package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.TicketDTO;
import cz.muni.fi.pa165.dto.TicketValidationAnswerDTO;
import cz.muni.fi.pa165.dto.TicketValidationRequestDTO;

import java.util.List;
import java.util.UUID;

public interface TicketFacade {

    List<TicketDTO> getAll();

    TicketDTO getById(Long id);

    List<TicketDTO> getByPerformance(Long id);

    List<TicketDTO> getByUser(Long id);

    TicketValidationAnswerDTO validateTicket(TicketValidationRequestDTO dto);

    boolean returnTicket(Long id);
}