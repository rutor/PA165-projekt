package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

public class CreateBookingDTO {
    @Getter @Setter
    private String description;

    @Getter @Setter
    private PerformanceDTO performance;

    @Getter @Setter
    private UserDTO user;
}