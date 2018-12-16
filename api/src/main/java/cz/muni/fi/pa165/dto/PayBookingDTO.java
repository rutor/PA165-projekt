package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.PaymentMethod;
import lombok.Data;

@Data
public class PayBookingDTO {
    private Long id;
    private PaymentMethod paymentMethod;
}