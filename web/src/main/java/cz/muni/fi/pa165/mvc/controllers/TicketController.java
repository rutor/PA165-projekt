package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.BookingDTO;
import cz.muni.fi.pa165.dto.TicketDTO;
import cz.muni.fi.pa165.facade.TicketFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * Tickets controller
 *
 * @author Tomas Rudolf
 */
@Controller
@RequestMapping("/ticketFacade")
public class TicketController {

    final static Logger log = LoggerFactory.getLogger(TicketController.class);

    @Inject
    private MessageSource messageSource; //resource bundle provided by Spring

    @Inject
    private TicketFacade ticketFacade;

    @RequestMapping({"/", "/list"})
    public String list(Model model) {
        log.info("/ or /list requested");
        List<TicketDTO> ticketsAll = ticketFacade.getAll();
        model.addAttribute("listOfTickets", ticketsAll);
        return "ticket/list";
    }

    @RequestMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        log.info("/{id} requested");
        TicketDTO ticket = ticketFacade.getById(id);
        model.addAttribute("ticket", ticket);
        return "ticket/detail";
    }

    @RequestMapping("/verify/{id}")
    public String verifyTicketWithId(@PathVariable Long id, Model model) {
        log.info("/verify/{id} requested");
        TicketDTO ticket = ticketFacade.getById(id);
        model.addAttribute("ticket", ticket);
        return "ticket/verification";
    }

    @RequestMapping("/return/{id}")
    public String returnById(@PathVariable Long id, Model model) {
        log.info("/return/{id} requested");
        boolean returned = ticketFacade.returnTicket(id);
        // FIXME Tomas milestone3 return back to list and show flash message about return of ticketFacade
        return "ticket/returned";
    }

}