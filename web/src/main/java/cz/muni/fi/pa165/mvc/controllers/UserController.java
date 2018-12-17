package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.CreateUserDTO;
import cz.muni.fi.pa165.dto.RoleDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.facade.RoleFacade;
import cz.muni.fi.pa165.facade.UserFacade;

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
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.List;

import static cz.muni.fi.pa165.mvc.controllers.BookingController.log;


@Controller
@RequestMapping(value = {WebUrls.URL_USER})
public class UserController {


    @Inject
    private UserFacade userFacade;

    @Inject
    private RoleFacade roleFacade;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof UserDTO) {
            // Converts from ID of User to UserDTO object before passing to function
            binder.registerCustomEditor(RoleDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String s) throws IllegalArgumentException {
                    log.debug("setAsText " + s + "");
                    Long id = Long.valueOf(s);
                    super.setValue(roleFacade.getRoleById(id));
                }
            });
        }
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Long roleId, Model model) {
        List<UserDTO> users;
        if(roleId == null) {
            users = userFacade.getAllUser();
        }
        else {
            users = userFacade.getAllUsersByRoleId(roleId);
        }
        model.addAttribute("users", users);
        return (WebUrls.URL_USER+"/list");
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        UserDTO user = userFacade.getUserById(id);
        try {
            userFacade.removeUser(id);
            redirectAttributes.addFlashAttribute("alert_success", "User \"" + user.getFirstName()+ user.getLastName() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger",
                    "User \"" + user.getFirstName()+ user.getLastName() + "\" cannot be deleted, delete its performances first, if possible.");
        }
        return "redirect:" + uriBuilder.path(WebUrls.URL_USER+"/").toUriString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("user", userFacade.getUserById(id));
        return (WebUrls.URL_USER+"/view");
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String confirmDelete(@PathVariable long id, Model model) {
        model.addAttribute("user", userFacade.getUserById(id));
        return (WebUrls.URL_USER+"/confirm_delete");
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    //public String newUser(@RequestParam(required = false) Long roleId, Model model) {
    public String newUser(Model model) {
        CreateUserDTO userCreate = new CreateUserDTO();
        List<RoleDTO> roles = roleFacade.getAllRole();
        model.addAttribute("roles", roles);
        model.addAttribute("userCreate", userCreate);
        return (WebUrls.URL_USER+"/new");
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("userCreate") CreateUserDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return (WebUrls.URL_USER+"/new");
        }
        formBean.setRoleId(formBean.getRoleId());
        formBean.setCreatedAt(LocalDate.now());
        Long id = userFacade.createUser(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "User " + id + " was created");
        return "redirect:" + uriBuilder.path(WebUrls.URL_USER+"/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model) {
        List<RoleDTO> allRoles = roleFacade.getAllRole();
        model.addAttribute("roles", allRoles);
        model.addAttribute("userEdit", userFacade.getUserById(id));
        return (WebUrls.URL_USER+"/edit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("userEdit") UserDTO formBean, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return (WebUrls.URL_USER+"/edit");
        }

        formBean.setCreatedAt(userFacade.getUserById(formBean.getId()).getCreatedAt());
        formBean.setUpdatedAt(LocalDate.now());
        userFacade.updateUser(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Your edits to the user " +formBean.getId() + " were successfully saved");
        return "redirect:" + uriBuilder.path(WebUrls.URL_USER+"/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }
}
