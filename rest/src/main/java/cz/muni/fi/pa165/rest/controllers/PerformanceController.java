package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.rest.ApiUris;

import javax.inject.Inject;

import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * Performance controller.
 *
 * @author Tomas Rudolf
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_PERFORMANCE)
public class PerformanceController {

    final static Logger logger = LoggerFactory.getLogger(PerformanceController.class);

    @Inject
    private PerformanceFacade performanceFacade;

    /**
     * Get list of Performances curl -i -X GET http://localhost:8080/pa165/rest/performance
     *
     * @return PerformanceDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PerformanceDTO> getPerformances() {
        logger.info("rest getPerformances()");
        return performanceFacade.getAllPerformances();
    }

    /**
     *
     * Get Performance by identifier id curl -i -X GET http://localhost:8080/pa165/rest/performance/1
     *
     * @param id identifier for a product
     * @return PerformanceDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PerformanceDTO getPerformance(@PathVariable("id") long id) throws Exception {
        logger.info("rest getPerformance({})", id);
        try {
            PerformanceDTO performanceDTO = performanceFacade.getPerformanceById(id);
            return performanceDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }
}