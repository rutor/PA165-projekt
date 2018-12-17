package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.ShowDTO;
import cz.muni.fi.pa165.facade.ShowFacade;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author xtrnkal
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    
    @Inject
    ShowFacade showFacade;
    
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Long genreId, Model model) {
        List<ShowDTO> shows;
        shows = showFacade.getAllShows();
        model.addAttribute("shows", shows);
        return "home/home";
    }
    
}
