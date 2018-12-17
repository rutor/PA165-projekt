package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.enums.AuthenticateUserStatus;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.services.BeanMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {WebUrls.Authentication})
public class AuthenticationController {

    final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Inject
    private UserFacade userFacade;

    @Inject
    private BeanMappingService mappingService;

    @RequestMapping(value = WebUrls.URL_LOGIN, method = RequestMethod.GET)
    public String authForm(
            Model model,
            HttpServletRequest req,
            HttpServletResponse res) {
        log.info("GET request:" +(WebUrls.Authentication+"/login"));
        if (req.getSession().getAttribute("authUser") != null) {
            return "redirect:/";
        }
        return (WebUrls.Authentication+"/authentication");
    }

    @RequestMapping(value = WebUrls.URL_LOGIN, method = RequestMethod.POST)
    public String authenticate(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {
        log.info("POST request:" + WebUrls.Authentication + WebUrls.URL_LOGIN);

        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO(email, password);
        Enum<AuthenticateUserStatus> authenticationStatus = userFacade.authenticate(userAuthenticateDTO);
        if(authenticationStatus == AuthenticateUserStatus.UNKNOWN_USER) {
            redirectAttributes.addFlashAttribute("alert_danger", "User with given email does not exist");
            return "redirect:" + WebUrls.Authentication + WebUrls.URL_LOGIN;
        } else if(authenticationStatus == AuthenticateUserStatus.BAD_PASSWORD) {
            redirectAttributes.addFlashAttribute("alert_danger", "Wrong password");
            return "redirect:" + WebUrls.Authentication + WebUrls.URL_LOGIN;
        }

        UserDTO user = userFacade.getUserByEmail(email);
        HttpSession session = req.getSession(true);
        session.setAttribute("authUser", user);
        log.info("POST request:" + WebUrls.Authentication + WebUrls.URL_LOGIN + "; user with email {} has been logged in", user.getEmail());
        redirectAttributes.addFlashAttribute("alert_info", "User with email " + user.getEmail() + " has been logged in.");
        return "redirect:/";
    }

    @RequestMapping(value = WebUrls.URL_LOGOUT, method = RequestMethod.GET)
    public String logout(
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req) {
        log.info("GET request:"+(WebUrls.URL_LOGOUT));
        HttpSession session = req.getSession(true);
        session.removeAttribute("authUser");
        redirectAttributes.addFlashAttribute("alert_info", "You have been successfully logged out.");
        return "redirect:"+(WebUrls.Authentication+WebUrls.URL_LOGIN);
    }
}
