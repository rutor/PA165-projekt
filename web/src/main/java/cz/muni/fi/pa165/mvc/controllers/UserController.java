package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dto.CreateUserDTO;
import cz.muni.fi.pa165.dto.RoleDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.facade.RoleFacade;

import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.entity.Users;

import cz.muni.fi.pa165.services.RoleService;
import cz.muni.fi.pa165.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.inject.Inject;
import java.time.LocalDate;

import cz.muni.fi.pa165.EntityUtils;

@Controller
//@RequestMapping(value = "/user")
@RequestMapping(value = {WebUrls.URL_USER})
public class UserController {
    @Inject
    private RoleFacade roleFacade;
    @Inject
    private UserFacade userFacade;

    @Inject
    private UserService userService;
    @Inject
    private RoleService roleService;

    /**
     * list of users.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("user", userFacade.getAllUser());
        return "user/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        UserDTO user = userFacade.getUserById(id);
        try {
            userFacade.removeUser(id);
            redirectAttributes.addFlashAttribute("alert_success", "User \"" + user.getLastName() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger",
                    "User \"" + user.getLastName() + "\" cannot be deleted, it might be used for a user.");
        }
        return "redirect:" + uriBuilder.path("/user/").toUriString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("user", userFacade.getUserById(id));
        model.addAttribute("role", roleFacade.getRoleById(userFacade.getUserById(id).getRole().getId()));
        return "user/view";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String confirmDelete(@PathVariable long id, Model model) {
        model.addAttribute("user", userFacade.getUserById(id));
        return "user/confirm_delete";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("userCreate", new CreateUserDTO());
        return "user/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("userCreate") CreateUserDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "user/new";
        }

//       Role role = EntityUtils.createRole(null, "User",  "User of system");
//
//        RoleDTO rolee =roleFacade.getRoleByName("User");
//        //roleService.create(rolee);
//        CreateRoleDTO roleee=new CreateRoleDTO();
//        roleee.setDescription("looser");
//        roleee.setName("Robo");
//        Long idd =roleFacade.createRole(roleee);
//
//
//        CreateUserDTO user = new CreateUserDTO();
//        user.setFirstName("Robo");
//        user.setLastName("Dudas");
//        user.setPassword("robo123");
//        user.setEmail("robo@robo.com");
//        user.setRoleId(rolee.getId());
//        Long id = userFacade.createUser(user);

        RoleDTO role =roleFacade.getRoleByName("User");
        formBean.setRoleId(role.getId());
        Long id = userFacade.createUser(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "User " + id + " was created");
        return "redirect:" + uriBuilder.path("/user/{id}").buildAndExpand(id).encode().toUriString();
    }
    /**
     * Prepares an edit form.
     * @param id Id of the user to edit
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("userEdit", userFacade.getUserById(id));;
        return "user/edit";
    }

    /**
     * Saves a user after an edit.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("userEdit") UserDTO formBean, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "user/edit";
        }
        UserDTO oldUser =userFacade.getUserById(formBean.getId());
        formBean.setCreatedAt(oldUser.getCreatedAt());
        formBean.setRole(oldUser.getRole());
        formBean.setUpdatedAt(LocalDate.now());
        userFacade.updateUser(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Your edits to the user " + formBean.getLastName() + " were successfully saved");
        return "redirect:" + uriBuilder.path("/user/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }
}

