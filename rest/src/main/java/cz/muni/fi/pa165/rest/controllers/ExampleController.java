package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.facade.PerformanceFacade;
import cz.muni.fi.pa165.rest.ApiUris;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * Example controller. Will be removed after implementation of our specific controllers
 *
 * @author brossi
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_PERFORMANCE)
public class ExampleController {

    final static Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @Inject
    private PerformanceFacade performanceFacade;

    /**
     * Get list of Products curl -i -X GET
     * http://localhost:8080/eshop-rest/products
     *
     * @return ProductDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PerformanceDTO> getPerformances() {
        logger.info("rest getPerformances()");
        return performanceFacade.getAllPerformances();
    }

    /**
     *
     * Get Product by identifier id curl -i -X GET
     * http://localhost:8080/eshop-rest/products/1
     *
     * @param id identifier for a product
     * @return ProductDTO
     * @throws ResourceNotFoundException
     */
    /*@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ProductDTO getProduct(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getProduct({})", id);

        try {
            ProductDTO productDTO = performanceFacade.getProductWithId(id);
            return productDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }*/

    /**
     * Delete one product by id curl -i -X DELETE
     * http://localhost:8080/eshop-rest/products/1
     *
     * @param id identifier for product
     * @throws ResourceNotFoundException
     */
    /*@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteProduct(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteProduct({})", id);
        try {
            performanceFacade.deleteProduct(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }*/

    /**
     * Create a new product by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data
     * '{"name":"test","description":"test","color":"UNDEFINED","price":"200",
     * "currency":"CZK", "categoryId":"1"}'
     * http://localhost:8080/eshop-rest/products/create
     *
     * @param product ProductCreateDTO with required fields for creation
     * @return the created product ProductDTO
     * @throws ResourceAlreadyExistingException
     */
    /*@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ProductDTO createProduct(@RequestBody ProductCreateDTO product) throws Exception {

        logger.debug("rest createProduct()");

        try {
            Long id = performanceFacade.createProduct(product);
            return performanceFacade.getProductWithId(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }*/

    /**
     * Update the price for one product by PUT method curl -X PUT -i -H
     * "Content-Type: application/json" --data '{"value":"16.33","currency":"CZK"}'
     * http://localhost:8080/eshop-rest/products/4
     *
     * @param id identified of the product to be updated
     * @param newPrice required fields as specified in NewPriceDTO
     * @return the updated product ProductDTO
     * @throws InvalidParameterException
     */
    /*@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ProductDTO changePrice(@PathVariable("id") long id, @RequestBody NewPriceDTO newPrice) throws Exception {

        logger.debug("rest changePrice({})", id);

        try {
            newPrice.setProductId(id);
            performanceFacade.changePrice(newPrice);
            return performanceFacade.getProductWithId(id);
        } catch (EshopServiceException esse) {
            throw new InvalidParameterException();
        }

    }*/

    /**
     * Add a new category by POST Method
     *
     * @param id the identifier of the Product to have the Category added
     * @param category the category to be added
     * @return the updated product as defined by ProductDTO
     * @throws InvalidParameterException
     */
    /*@RequestMapping(value = "/{id}/categories", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ProductDTO addCategory(@PathVariable("id") long id, @RequestBody CategoryDTO category) throws Exception {

        logger.debug("rest addCategory({})", id);

        try {
            performanceFacade.addCategory(id, category.getId());
            return performanceFacade.getProductWithId(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }*/
}