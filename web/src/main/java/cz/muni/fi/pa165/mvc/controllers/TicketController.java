package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.dto.TicketDTO;
import cz.muni.fi.pa165.dto.TicketValidationAnswerDTO;
import cz.muni.fi.pa165.dto.TicketValidationRequestDTO;
import cz.muni.fi.pa165.enums.TicketStatus;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.facade.TicketFacade;
import cz.muni.fi.pa165.mvc.ViewErrorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Tickets controller
 *
 * @author Tomas Rudolf
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {

    final static Logger log = LoggerFactory.getLogger(TicketController.class);

    @Inject
    private MessageSource messageSource; //resource bundle provided by Spring

    @Inject
    private TicketFacade ticketFacade;

    @Inject
    private PerformanceFacade performanceFacade;

    @RequestMapping(value = {"/", "/list", ""}, method = RequestMethod.GET)
    public String list(Model model) {
        log.info("/ or /list requested");
        List<TicketDTO> ticketsAll = ticketFacade.getAll();
        model.addAttribute("listOfTickets", ticketsAll);
        return "ticket/list";
    }

    @RequestMapping(value = "/{barcodeString}", method = RequestMethod.GET)
    public String getByBarcode(@PathVariable String barcodeString, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.info("/{barcodeString} requested");
        UUID barcode = UUID.fromString(barcodeString);
        TicketDTO ticket = ticketFacade.getByBarcode(barcode);
        if (ticket == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "No ticket with barcode: " + barcodeString + " exists");
            return "redirect:" + uriBuilder.path("/ticket").toUriString();
        }
        model.addAttribute("ticket", ticket);
        return "ticket/detail";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String verifyTicketForm(Model model) {
        log.info("/verify GET requested");

        TicketValidationRequestDTO validationDTO = new TicketValidationRequestDTO();
        model.addAttribute("validationDTO", validationDTO);

        List<PerformanceDTO> performances = performanceFacade.getAllPerformances();
        model.addAttribute("performances", performances);
        return "ticket/verify";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String verifyTicketWithId(@Valid @ModelAttribute("validationDTO") TicketValidationRequestDTO dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.info("/verify POST requested");
        if (bindingResult.hasErrors()) {
            ViewErrorUtils.handleErrorInBinding(bindingResult, model);
            return "ticket/verify";
        }
        TicketValidationAnswerDTO answerDTO = ticketFacade.validateTicket(dto);
        if (answerDTO == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Ticket with barcode " + dto.getBarcode() + " does not exist");
            return "redirect:" + uriBuilder.path("/ticket").toUriString();
        }
        if (answerDTO.isValid()) {
            if (answerDTO.getTicketStatus() == TicketStatus.NOT_USED) {
                redirectAttributes.addFlashAttribute("alert_success", "Ticket with barcode " + dto.getBarcode() + " is valid for given performance");
            }
            if (answerDTO.getTicketStatus() == TicketStatus.USED) {
                redirectAttributes.addFlashAttribute("alert_danger", "Ticket with barcode " + dto.getBarcode() + " is already used");
            }
            if (answerDTO.getTicketStatus() == TicketStatus.RETURNED) {
                redirectAttributes.addFlashAttribute("alert_danger", "Ticket with barcode " + dto.getBarcode() + " is already returned");
            }
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "Ticket with barcode " + dto.getBarcode() + " is not for this performance");
        }
        return "redirect:" + uriBuilder.path("/ticket/" + dto.getBarcode()).toUriString();
    }

    @RequestMapping(value = "/return/{barcodeString}", method = RequestMethod.GET)
    public String returnForm(@PathVariable String barcodeString, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.info("/return/{barcodeString} requested");
        // FIXME Tomas milestone3 Check that currently logged user is admin
        model.addAttribute("barcode", barcodeString);
        return "ticket/return";
    }

    @RequestMapping(value = "/return/{barcodeString}", method = RequestMethod.POST)
    public String returnById(@PathVariable String barcodeString, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.info("/return/{barcodeString} requested");
        // FIXME Tomas milestone3 Check that currently logged user is admin
        UUID barcode = UUID.fromString(barcodeString);
        TicketDTO ticket = ticketFacade.getByBarcode(barcode);
        if (ticket == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "No ticket with barcode: " + barcodeString + " exists");
            return "redirect:" + uriBuilder.path("/ticket").toUriString();
        }
        boolean returned = ticketFacade.returnTicket(ticket.getId());
        if (returned) {
            redirectAttributes.addFlashAttribute("alert_success", "Ticket with barcode: " + barcodeString + " was successfully returned");
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "Ticket with barcode: " + barcodeString + " cannot be returned");
        }
        return "redirect:" + uriBuilder.path("/ticket").toUriString();
    }

    @RequestMapping(value = "/use/{barcodeString}", method = RequestMethod.GET)
    public String useTicket(@PathVariable String barcodeString, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.info("/use/{barcodeString} requested");
        UUID barcode = UUID.fromString(barcodeString);
        TicketDTO ticket = ticketFacade.getByBarcode(barcode);
        if (ticket == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "No ticket with barcode: " + barcodeString + " exists");
            return "redirect:" + uriBuilder.path("/ticket").toUriString();
        }
        boolean used = ticketFacade.useTicket(barcode);
        if (used) {
            redirectAttributes.addFlashAttribute("alert_success", "Ticket with barcode: " + barcodeString + " was successfully used");
        } else {
            redirectAttributes.addFlashAttribute("alert_danger", "Ticket with barcode: " + barcodeString + " was already used before");
        }
        return "redirect:" + uriBuilder.path("/ticket/{barcodeString}").buildAndExpand(barcodeString).encode().toUriString();
    }

}