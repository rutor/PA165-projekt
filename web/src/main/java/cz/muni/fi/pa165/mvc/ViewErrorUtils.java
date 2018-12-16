package cz.muni.fi.pa165.mvc;

import cz.muni.fi.pa165.mvc.controllers.BookingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ViewErrorUtils {

    final static Logger log = LoggerFactory.getLogger(BookingController.class);

    public static void handleErrorInBinding(BindingResult bindingResult, Model model) {
        for (ObjectError ge : bindingResult.getGlobalErrors()) {
            log.trace("ObjectError: {}", ge);
        }
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
            log.trace("FieldError: {}", fe);
        }
    }
}
