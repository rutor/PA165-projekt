package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.TicketDTO;
import cz.muni.fi.pa165.dto.TicketValidationAnswerDTO;
import cz.muni.fi.pa165.dto.TicketValidationRequestDTO;
import cz.muni.fi.pa165.entity.Ticket;
import cz.muni.fi.pa165.enums.TicketStatus;
import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.services.TicketService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TicketFacadeImpl implements TicketFacade{

    @Inject
    TicketService ticketService;

    @Inject
    BeanMappingService beanMappingService;

    @Override
    public List<TicketDTO> getAll() {
        return beanMappingService.mapTo(ticketService.getAll(), TicketDTO.class);
    }

    @Override
    public TicketDTO getById(Long id) {
        return beanMappingService.mapTo(ticketService.getById(id), TicketDTO.class);
    }

    @Override
    public TicketDTO getByBarcode(UUID barcode) {
        return beanMappingService.mapTo(ticketService.getByBarcode(barcode), TicketDTO.class);
    }

    @Override
    public List<TicketDTO> getByPerformance(Long id) {
        return beanMappingService.mapTo(ticketService.getByPerformance(id), TicketDTO.class);
    }

    @Override
    public List<TicketDTO> getByUser(Long id) {
        return beanMappingService.mapTo(ticketService.getByUser(id), TicketDTO.class);
    }

    @Override
    public TicketValidationAnswerDTO validateTicket(TicketValidationRequestDTO dto) {
        TicketValidationAnswerDTO validationDto = new TicketValidationAnswerDTO();
        Ticket ticket = ticketService.getByBarcode(dto.getBarcode());
        if (ticket == null) {
            return null;
        } else {
            if (ticket.getPerformance().getId().equals(dto.getPerformanceId())) {
                TicketStatus status = ticket.getStatus();
                validationDto.setTicketStatus(status);
                if (status.equals(TicketStatus.NOT_USED)) {
                    validationDto.setValid(true);
                }
            }
        }
        return validationDto;
    }

    @Override
    public boolean returnTicket(Long id) {
        return ticketService.returnTicket(id);
    }

    @Override
    public boolean useTicket(UUID barcode) {
        return ticketService.useTicket(barcode);
    }
}
