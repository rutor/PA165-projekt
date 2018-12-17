package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.enums.AuthenticateUserStatus;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.services.BeanMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
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
import java.util.Enumeration;

@Controller
@RequestMapping(value = {WebUrls.URL_AUTH})
public class AuthenticationController {

    final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Inject
    private UserFacade userFacade;

    @Inject
    private BeanMappingService mappingService;

    @RequestMapping(value = WebUrls.URL_LOGIN, method = RequestMethod.GET)
    public String authForm(
            HttpServletRequest req) {
        log.info("GET request:" +(WebUrls.URL_AUTH +"/login"));
        if (req.getSession(true).getAttribute("authUser") != null) {
            return "redirect:/";
        }
        return WebUrls.URL_AUTH + "/authentication";
    }

    @RequestMapping(value = WebUrls.URL_LOGOUT, method = RequestMethod.GET)
    public String logout() {
        log.info("GET request: "+ WebUrls.URL_LOGOUT);
        return WebUrls.URL_AUTH + WebUrls.URL_LOGOUT;
    }
}
