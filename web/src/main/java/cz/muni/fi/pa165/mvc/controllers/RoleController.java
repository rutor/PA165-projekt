package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dto.RoleDTO;
import cz.muni.fi.pa165.facade.RoleFacade;

import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.inject.Inject;


/**
 * Implements the role management and display interface.
 *
 * @author xdudasr
 */
@Controller
@RequestMapping(value = {WebUrls.URL_ROLE})
public class RoleController {

    @Inject
    private RoleFacade roleFacade;
    @Inject
    private UserFacade userFacade;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("roles", roleFacade.getAllRole());
        return "role/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        RoleDTO role = roleFacade.getRoleById(id);
        try {
            roleFacade.removeRole(id);
            redirectAttributes.addFlashAttribute("alert_success", "Role \"" + role.getName() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Role \"" + role.getName() + "\" cannot be deleted, it might be used for a show.");
        }
        return "redirect:" + uriBuilder.path("/role/").toUriString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("role", roleFacade.getRoleById(id));
        model.addAttribute("user", userFacade.getAllUsersByRoleId(id));
        return "role/view";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String confirmDelete(@PathVariable long id, Model model) {
        model.addAttribute("role", roleFacade.getRoleById(id));
        return "role/confirm_delete";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRole(Model model) {
        model.addAttribute("roleCreate", new CreateRoleDTO());
        return "role/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("roleCreate") CreateRoleDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "role/new";
        }
        Long id = roleFacade.createRole(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Role " + id + " was created");
        return "redirect:" + uriBuilder.path(WebUrls.URL_ROLE+"/{id}").buildAndExpand(id).encode().toUriString();
    }
    /**
     * Prepares an edit form.
     * @param id Id of the role to edit
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editRole(@PathVariable Long id, Model model) {
        model.addAttribute("roleEdit", roleFacade.getRoleById(id));
        return "role/edit";
    }

    /**
     * Saves a role after an edit.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("roleEdit") RoleDTO formBean, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "role/edit";
        }
        roleFacade.updateRole(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Your edits to the role " + formBean.getName() + " were successfully saved");
        return "redirect:" + uriBuilder.path(WebUrls.URL_ROLE+"/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }
}

