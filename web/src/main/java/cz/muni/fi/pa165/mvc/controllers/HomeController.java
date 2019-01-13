package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.CreateBookingDTO;
import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.dto.ShowDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.BookingFacade;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.facade.ShowFacade;
import cz.muni.fi.pa165.facade.TicketFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.mvc.ViewErrorUtils;
import static cz.muni.fi.pa165.mvc.controllers.BookingController.log;
import java.beans.PropertyEditorSupport;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
    UserFacade userFacade;
    
    @Inject
    BookingFacade bookingFacade;
    
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
    
    @RequestMapping(value = "/show_detail/{id}", method = RequestMethod.POST)
    public String addBooking(@PathVariable long id, @Valid @ModelAttribute("bookingCreate") CreateBookingDTO dto, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(dto={})", dto);
        if (bindingResult.hasErrors()) {
            ViewErrorUtils.handleErrorInBinding(bindingResult, model);
            return "show_detail/"+id;
        }
        
        UserDTO userDTO = userFacade.getUserById(1l);
        dto.setUser(userDTO);
        Long idBook = bookingFacade.create(dto);
        redirectAttributes.addFlashAttribute("alert_success", "Booking with id " + idBook + " was created");
        return "redirect:" + uriBuilder.path("show_detail").toUriString();
    }
    
    @RequestMapping(value = "/buy_ticket/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable long id, Model model) {
        model.addAttribute("performance", performanceFacade.getPerformanceById(id));
        
        
        return "buy_ticket";
    }
    
    @RequestMapping(value = "/bookings_tickets/{id}", method = RequestMethod.GET)
    public String tickets(@PathVariable long id, Model model) {
        model.addAttribute("tickets", ticketFacade.getByUser(id));
        return "bookings_tickets";
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
    
}
