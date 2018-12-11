package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.BookingDTO;
import cz.muni.fi.pa165.dto.CreateBookingDTO;
import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.facade.BookingFacade;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.services.BeanMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bookings controller
 *
 * @author Tomas Rudolf
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

    final static Logger log = LoggerFactory.getLogger(BookingController.class);

    @Inject
    private MessageSource messageSource; //resource bundle provided by Spring

    @Inject
    private BookingFacade bookingFacade;

    @Inject
    private UserFacade userFacade;

    @Inject
    private PerformanceFacade performanceFacade;

    @Inject
    private BeanMappingService beanMappingService;

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String list(Model model) {
        log.info("/ or /list requested");
        List<BookingDTO> bookingsList = bookingFacade.getAll();
        model.addAttribute("listOfBookings", bookingsList);
        return "booking/list";
    }

    @RequestMapping(value = "/createForm", method = RequestMethod.GET)
    public String createForm(Model model) {
        log.info("/createForm requested");
        List<PerformanceDTO> allPerformances = performanceFacade.getAllPerformances();
        model.addAttribute("performances", allPerformances.stream().map(performance -> performance.getShow().getName() + " at " + performance.getStartDate().toString()).collect(Collectors.toList()));
        model.addAttribute("bookingCreate", new CreateBookingDTO());
        return "booking/create_form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("bookingCreate") CreateBookingDTO dto, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(dto={})", dto);
        if (bindingResult.hasErrors()) {
            handleErrorInValidation(bindingResult, model);
            return "booking/create_form";
        }
        //FIXME Tomas milestone3 use currently logged-in user
        Long id = bookingFacade.create(dto);
        redirectAttributes.addFlashAttribute("alert_success", "Booking with id " + id + " was created");
        return "redirect:" + uriBuilder.path("/booking/list").toUriString();
    }

    private void handleErrorInValidation(BindingResult bindingResult, Model model) {
        for (ObjectError ge : bindingResult.getGlobalErrors()) {
            log.trace("ObjectError: {}", ge);
        }
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
            log.trace("FieldError: {}", fe);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable Long id, Model model) {
        log.info("/{id} requested");
        BookingDTO booking = bookingFacade.getById(id);
        model.addAttribute("booking", booking);
        return "booking/detail";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable Long id, Model model) {
        log.info("/delete/{id} requested");
        //BookingDTO booking = bookingFacade.remove(id);
        //model.addAttribute("booking", booking);
        // FIXME Tomas milestone3 return back to list and show flash message about removal of booking
        return "booking/removed";
    }

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    public String payBooking(@PathVariable Long id, Model model) {
        log.info("/pay/{id} requested");
        //BookingDTO booking = bookingFacade.remove(id);
        //model.addAttribute("booking", booking);
        // FIXME Tomas milestone3 add flash message about paying of booking
        return "ticket/detail";
    }

}