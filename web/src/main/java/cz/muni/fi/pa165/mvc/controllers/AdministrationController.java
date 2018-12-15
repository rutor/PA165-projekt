package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.facade.ShowFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author xtrnkal
 */
@Controller
@RequestMapping(value = "/administration")
public class AdministrationController {

    	@Inject
	private GenreFacade genreFacade;
	@Inject
	private ShowFacade showFacade;
    
    /**
	 * Shows a list of genres, for admins with the ability to add, delete or edit.
	 * 
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("genres", genreFacade.getAllGenres());
		return "administration";
	}
    
}
