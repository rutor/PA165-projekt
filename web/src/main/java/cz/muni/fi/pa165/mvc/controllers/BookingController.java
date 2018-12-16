package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.enums.PaymentMethod;
import cz.muni.fi.pa165.facade.BookingFacade;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.mvc.ViewErrorUtils;
import cz.muni.fi.pa165.services.BeanMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;

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

    @RequestMapping(value = {"/", "/list", ""}, method = RequestMethod.GET)
    public String list(Model model) {
        log.info("/ or /list requested");
        List<BookingDTO> bookingsList = bookingFacade.getAll();
        model.addAttribute("listOfBookings", bookingsList);
        return "booking/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        log.info("/create requested");
        List<PerformanceDTO> allPerformances = performanceFacade.getAllPerformances();
        model.addAttribute("performances", allPerformances);
        model.addAttribute("bookingCreate", new CreateBookingDTO());
        return "booking/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("bookingCreate") CreateBookingDTO dto, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(dto={})", dto);
        if (bindingResult.hasErrors()) {
            ViewErrorUtils.handleErrorInBinding(bindingResult, model);
            return "booking/create_form";
        }
        //FIXME Tomas milestone3 use currently logged-in user
        UserDTO userDTO = userFacade.getUserById(1l);
        dto.setUser(userDTO);
        Long id = bookingFacade.create(dto);
        redirectAttributes.addFlashAttribute("alert_success", "Booking with id " + id + " was created");
        return "redirect:" + uriBuilder.path("/booking/list").toUriString();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CreateBookingDTO) {
            // Converts from ID of Performance to PerformanceDTO object before passing to function
            binder.registerCustomEditor(PerformanceDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String s) throws IllegalArgumentException {
                    log.debug("setAsText `" + s + "`");
                    Long id = Long.valueOf(s);
                    super.setValue(performanceFacade.getPerformanceById(id));
                }
            });
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        log.info("/{id} requested");
        BookingDTO booking = bookingFacade.getById(id);
        if (booking == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "No booking with id: " + id + " exits");
            return "redirect:" + uriBuilder.path("/booking/list").toUriString();
        }
        model.addAttribute("booking", booking);
        return "booking/detail";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteAskConfirmation(@PathVariable Long id, Model model) {
        log.info("/delete/{id} requested");
        model.addAttribute("id", id);
        return "booking/delete";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteById(@PathVariable Long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        log.info("/delete/{id} POST requested");
        boolean removed = bookingFacade.remove(id);
        if (removed) {
            redirectAttributes.addFlashAttribute("alert_success", "Booking with id: " + id + " was successfully removed");
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "Booking with id: " + id + " cannot be removed");
        }
        return "redirect:" + uriBuilder.path("/booking/list").toUriString();
    }

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    public String payBookingForm(@PathVariable Long id, Model model) {
        log.info("/pay/{id} requested");
        // FIXME Tomas milestone3 check that currently logged-in user is admin
        PayBookingDTO dto = new PayBookingDTO();
        dto.setId(id);
        model.addAttribute("payDTO", dto);
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "booking/pay";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String payBooking(@Valid @ModelAttribute("payDTO") PayBookingDTO dto, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        log.info("/pay POST requested");
        // FIXME Tomas milestone3 check that currently logged-in user is admin
        log.debug(dto.toString());
        TicketDTO ticket = bookingFacade.pay(dto);
        if (ticket != null) {
            redirectAttributes.addFlashAttribute("alert_success", "Successfully payed for booking with id: " + dto.getId());
            return "redirect:" + uriBuilder.path("/ticket/" + ticket.getBarcode()).toUriString();
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "Booking with id: " + dto.getId() + " cannot be payed");
            return "redirect:" + uriBuilder.path("/booking/list").toUriString();
        }
    }

}