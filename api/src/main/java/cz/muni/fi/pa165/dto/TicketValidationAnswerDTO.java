package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

public class TicketValidationAnswerDTO {

    @Getter @Setter
    /**
     * Default status is TicketStatus.RETURNED as it defines invalid ticket
     */
    private TicketStatus ticketStatus = TicketStatus.RETURNED;

    @Getter @Setter
    /**
     * Default value is false as only if ticket is valid it will be set to true
     */
    private boolean valid = false;
}
