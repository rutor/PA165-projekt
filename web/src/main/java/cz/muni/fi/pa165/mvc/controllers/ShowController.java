package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.CreateShowDTO;
import cz.muni.fi.pa165.dto.ShowDTO;
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
 * Implements the show management and display interface.
 * 
 * @author tyrylu
 */
@Controller
@RequestMapping(value = "/show")
public class ShowController {

	@Inject
	private ShowFacade showFacade;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(@RequestParam(required = false) Long genreId, Model model) {
		List<ShowDTO> shows;
		if(genreId == null) {
			shows = showFacade.getAllShows();
		}
		else {
			shows = showFacade.getAllShowsByGenreId(genreId);
		}
		model.addAttribute("shows", shows);
		return "show/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
			RedirectAttributes redirectAttributes) {
		ShowDTO show = showFacade.getShowById(id);
		try {
			showFacade.removeShow(id);
			redirectAttributes.addFlashAttribute("alert_success", "Show \"" + show.getName() + "\" was deleted.");
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("alert_danger",
					"Show \"" + show.getName() + "\" cannot be deleted, delete its performances first, if possible.");
		}
		return "redirect:" + uriBuilder.path("/show/").toUriString();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("show", showFacade.getShowById(id));
		return "show/view";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable long id, Model model) {
		model.addAttribute("show", showFacade.getShowById(id));
		return "show/confirm_delete";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newShow(@RequestParam(required = false) Long genreId, Model model) {
		
		CreateShowDTO showCreate = new CreateShowDTO();
		showCreate.setGenreId(genreId);
		model.addAttribute("showCreate", showCreate);
		return "genre/new";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("showCreate") CreateShowDTO formBean, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
			}
			return "show/new";
		}
		Long id = showFacade.createShow(formBean);
		redirectAttributes.addFlashAttribute("alert_success", "Show " + id + " was created");
		return "redirect:" + uriBuilder.path("/show/{id}").buildAndExpand(id).encode().toUriString();
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editShow(@PathVariable Long id, Model model) {
		model.addAttribute("showEdit", showFacade.getShowById(id));
		return "show/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("showEdit") ShowDTO formBean, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
			}
			return "show/edit";
		}
		showFacade.updateShow(formBean);
		redirectAttributes.addFlashAttribute("alert_success", "Your edits to the show " + formBean.getName() + " were successfully saved");
		return "redirect:" + uriBuilder.path("/show/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
	}
}
