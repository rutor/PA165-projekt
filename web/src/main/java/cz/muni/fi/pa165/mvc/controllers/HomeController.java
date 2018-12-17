package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.dto.ShowDTO;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.facade.ShowFacade;
import cz.muni.fi.pa165.facade.TicketFacade;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author xtrnkal
 */
@Controller
@RequestMapping(value = "")
public class HomeController {
    
    @Inject
    ShowFacade showFacade;
    
    @Inject
    PerformanceFacade performanceFacade;
    
    @Inject
    TicketFacade ticketFacade;
    
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Long genreId, Model model) {
        List<ShowDTO> shows;
        shows = showFacade.getAllShows();
        model.addAttribute("shows", shows);
        return "home";
    }
    
    @RequestMapping(value = "/show_detail/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("show", showFacade.getShowById(id));
        List<PerformanceDTO> performances = performanceFacade.getAllPerfomancesByShowId(id);
        model.addAttribute("performances", performances);
        return "show_detail";
    }
    
    @RequestMapping(value = "/bookings_tickets", method = RequestMethod.GET)
    public String tickets(@RequestParam(required = true) Long userId,Model model) {
        model.addAttribute("tickets", ticketFacade.getByUser(userId));
        return "bookings_tickets";
    }
    
    
}
