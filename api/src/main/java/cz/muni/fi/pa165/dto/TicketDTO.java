package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

public class TicketDTO {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private UUID barcode;

    // FIXME Tomas milestone2 Uncomment when DTO classes are in repository
    /*@Getter @Setter
    private PerformanceDTO performance;

    @Getter @Setter
    private UsersDTO user;*/

    @Getter @Setter
    private LocalDate createdAt;

    @Getter @Setter
    private LocalDate updatedAt;
}