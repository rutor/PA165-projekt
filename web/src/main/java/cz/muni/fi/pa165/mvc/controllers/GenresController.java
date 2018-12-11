package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.CreateGenreDTO;
import cz.muni.fi.pa165.dto.GenreDTO;
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
 * Implements the genre management and display interface.
 * 
 * @author tyrylu
 */
@Controller
@RequestMapping(value = "/genre")
public class GenresController {

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
		return "genre/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
			RedirectAttributes redirectAttributes) {
		GenreDTO genre = genreFacade.getGenreById(id);
		try {
			genreFacade.removeGenre(id);
			redirectAttributes.addFlashAttribute("alert_success", "Genre \"" + genre.getName() + "\" was deleted.");
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("alert_danger",
					"Genre \"" + genre.getName() + "\" cannot be deleted, it might be used for a show.");
		}
		return "redirect:" + uriBuilder.path("/genre/").toUriString();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("genre", genreFacade.getGenreById(id));
		model.addAttribute("shows", showFacade.getAllShowsByGenreId(id));
		return "genre/view";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable long id, Model model) {
		model.addAttribute("genre", genreFacade.getGenreById(id));
		return "genre/confirm_delete";
	}

	/**
	 * Prepares an empty form.
	 *
	 * @param model data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newGenre(Model model) {
		model.addAttribute("genreCreate", new CreateGenreDTO());
		return "genre/new";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("genreCreate") CreateGenreDTO formBean, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
			}
			return "genre/new";
		}
		Long id = genreFacade.createGenre(formBean);
		redirectAttributes.addFlashAttribute("alert_success", "Genre " + id + " was created");
		return "redirect:" + uriBuilder.path("/genre/{id}").buildAndExpand(id).encode().toUriString();
	}
	/**
	 * Prepares an edit form.
	 * @param id Id of the genre to edit
	 * @param model data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editGenre(@PathVariable Long id, Model model) {
		model.addAttribute("genreEdit", genreFacade.getGenreById(id));
		return "genre/edit";
	}

	/**
 * Saves a genre after an edit.
 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("genreEdit") GenreDTO formBean, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		if (bindingResult.hasErrors()) {
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
			}
			return "genre/edit";
		}
		genreFacade.updateGenre(formBean);
		redirectAttributes.addFlashAttribute("alert_success", "Your edits to the genre " + formBean.getName() + " were successfully saved");
		return "redirect:" + uriBuilder.path("/genre/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
	}
}
