package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookingDTO {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private PaymentStatus paymentStatus;

    @Getter @Setter
    private TicketDTO ticket;

    @Getter @Setter
    private String description;

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