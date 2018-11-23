package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class TicketValidationRequestDTO {

    @Getter @Setter
    UUID barcode;

    @Getter @Setter
    Long performanceId;
}
