package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

public class PayBookingDTO {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private PaymentMethod method;
}