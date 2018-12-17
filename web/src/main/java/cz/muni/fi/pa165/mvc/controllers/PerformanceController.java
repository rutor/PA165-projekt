package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.CreateBookingDTO;
import cz.muni.fi.pa165.dto.CreatePerformanceDTO;
import cz.muni.fi.pa165.dto.HallDTO;
import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.dto.ShowDTO;
import cz.muni.fi.pa165.facade.HallFacade;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.facade.ShowFacade;
import static cz.muni.fi.pa165.mvc.controllers.BookingController.log;
import org.springframework.stereotype.Controller;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;



import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hibernate.validator.constraints.SafeHtml.Attribute;

/**
 *
 * @author xtrnkal
 */
@Controller
@RequestMapping(value = "/performance")
public class PerformanceController {

    @Inject
    private PerformanceFacade performanceFacade;

    @Inject
    private HallFacade hallFacade;

    @Inject
    private ShowFacade showFacade;

    /**
     * Shows a list of genres, for admins with the ability to add, delete or
     * edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("performances", performanceFacade.getAllPerformances());
        return "performance/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("performance", performanceFacade.getPerformanceById(id));
        return "performance/view";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerformance(Model model) {
        List<ShowDTO> allShows = showFacade.getAllShows();
        model.addAttribute("shows", allShows);
        
        List<HallDTO> allHalls = hallFacade.getAllHalls();
        model.addAttribute("halls", allHalls);
        
        model.addAttribute("performanceCreate", new CreatePerformanceDTO());
        return "performance/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("performanceCreate") CreatePerformanceDTO dto, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        log.debug("create(dto={})", dto);
        if (bindingResult.hasErrors()) {
            handleErrorInValidation(bindingResult, model);
            return "performance/new";
        }
        //FIXME Tomas milestone3 use currently logged-in user
        
        Long id = performanceFacade.createPerformance(dto);
        redirectAttributes.addFlashAttribute("alert_success", "Performance with id " + id + " was created");
        return "redirect:" + uriBuilder.path("/performance/list").toUriString();
    }

    private void handleErrorInValidation(BindingResult bindingResult, Model model) {
                for (ObjectError ge : bindingResult.getGlobalErrors()) {
            log.trace("ObjectError: {}", ge);
        }
        for (FieldError fe : bindingResult.getFieldErrors()) {
            model.addAttribute(fe.getField() + "_error", true);
            log.trace("FieldError: {}", fe);
        }
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
        List<ShowDTO> allShows = showFacade.getAllShows();
        model.addAttribute("shows", allShows);
        List<HallDTO> allHalls = hallFacade.getAllHalls();
        model.addAttribute("halls", allHalls);
        
        model.addAttribute("performanceEdit", performanceFacade.getPerformanceById(id));
        return "performance/edit";
    }

    /**
     * Saves a hall after an edit.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("performanceEdit") PerformanceDTO formBean, 
            BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "performance/edit";
        }
        
        //formBean.setHall(hallFacade.getHallById(hallBean));
        //formBean.setShow(showFacade.getShowById(showBean));
        performanceFacade.updatePerformance(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Your edits to the performance " + formBean.getId() + " were successfully saved");
        return "redirect:" + uriBuilder.path("/performance/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }
    
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes) {
        PerformanceDTO performance = performanceFacade.getPerformanceById(id);
        try {
            performanceFacade.removePerformance(id);
            redirectAttributes.addFlashAttribute("alert_success", "Performance \"" + performance.getId() + "\" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger",
                    "Performance \"" + performance.getId() + "\" cannot be deleted, it might be used for a show.");
        }
        return "redirect:" + uriBuilder.path("/performance/").toUriString();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String confirmDelete(@PathVariable long id, Model model) {
        model.addAttribute("performance", performanceFacade.getPerformanceById(id));
        return "performance/confirm_delete";
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof PerformanceDTO) {
            binder.registerCustomEditor(HallDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String s) throws IllegalArgumentException {
                    log.debug("setAsText `" + s + "`");
                    Long id = Long.valueOf(s);
                    super.setValue(hallFacade.getHallById(id));
                }
            });
            binder.registerCustomEditor(ShowDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String s) throws IllegalArgumentException {
                    log.debug("setAsText `" + s + "`");
                    Long id = Long.valueOf(s);
                    super.setValue(showFacade.getShowById(id));
                }
            });
            
            binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String s) throws IllegalArgumentException {
                    log.debug("setAsText `" + s + "`");
                    LocalDateTime time = LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    super.setValue(time);
                }
            });
        }
        if (binder.getTarget() instanceof CreatePerformanceDTO) {
            binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String s) throws IllegalArgumentException {
                    log.debug("setAsText `" + s + "`");
                    LocalDateTime time = LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    super.setValue(time);
                }
            });
        }
    }

    
}
