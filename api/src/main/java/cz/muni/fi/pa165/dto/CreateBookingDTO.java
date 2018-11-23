package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

public class CreateBookingDTO {
    @Getter @Setter
    private String description;

    // FIXME Tomas milestone2 Uncomment when DTO classes are in repository
    /*@Getter @Setter
    private PerformanceDTO performance;

    @Getter @Setter
    private UsersDTO user;*/
}