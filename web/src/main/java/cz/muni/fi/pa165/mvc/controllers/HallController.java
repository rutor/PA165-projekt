package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.HallDTO;
import cz.muni.fi.pa165.facade.HallFacade;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author xtrnkal
 */
@Controller
@RequestMapping(value = "/hall")
public class HallController {

    @Inject
    private HallFacade hallFacade;

    /**
     * Shows a list of halls, for admins with the ability to add, delete or
     * edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("halls", hallFacade.getAllHalls());
        return "hall/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("hall", hallFacade.getHallById(id));
        return "hall/view";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newHall(Model model) {
        model.addAttribute("hallCreate", new HallDTO());
        return "hall/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("hallCreate") HallDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "hall/new";
        }
        Long id = hallFacade.createHall(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Hall" + id + " was created");
        return "redirect:" + uriBuilder.path("/hall/{id}").buildAndExpand(id).encode().toUriString();
    }

    /**
     * Prepares an edit form.
     *
     * @param id Id of the hall to edit
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editHall(@PathVariable Long id, Model model) {
        model.addAttribute("hallEdit", hallFacade.getHallById(id));
        return "hall/edit";
    }

    /**
     * Saves a hall after an edit.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("hallEdit") HallDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "hall/edit";
        }
        hallFacade.updateHall(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Your edits to the hall " + formBean.getName() + " were successfully saved");
        return "redirect:" + uriBuilder.path("/hall/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes) {
        HallDTO hall = hallFacade.getHallById(id);
        try {
            hallFacade.removeHall(id);
            redirectAttributes.addFlashAttribute("alert_success", "Hall \"" + hall.getName() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Genre \"" + hall.getName() + "\" cannot be deleted, it might be used for a show.");
        }
        return "redirect:" + uriBuilder.path("/hall/").toUriString();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String confirmDelete(@PathVariable long id, Model model) {
        model.addAttribute("hall", hallFacade.getHallById(id));
        return "hall/confirm_delete";
    }
}
