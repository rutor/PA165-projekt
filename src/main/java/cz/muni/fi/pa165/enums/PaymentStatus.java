package cz.muni.fi.pa165.enums;

import lombok.*;
import javax.validation.constraints.*;

/**
 * Status of payment
 * @author Tomas Rudolf
 */
public enum PaymentStatus {

    NOT_PAYED("Not payed yet"),
    PROCESSING("Payment is in processing"),
    PAYED("Already payed");

    @Getter @Setter
    @NotNull
    private String description;

    PaymentStatus (String description) {
        setDescription(description);
    }
}
