package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author xtrnkal
 */
@Controller
@RequestMapping(value = "/administration")
public class AdministrationController {;
    
    /**
	 * Shows administration section
	 * 
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		return "administration";
	}
    
}
