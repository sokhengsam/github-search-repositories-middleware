package com.khalibre.assignment.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khalibre.assignment.dto.Repository;
import com.khalibre.assignment.dto.ResponseList;
import com.khalibre.assignment.services.RestClientService;
import com.khalibre.assignment.services.SearchRequestBuilder;

/**
 * Github search repositories proxy controller
 * 
 * @author Sokheng SAM
 * @since 03/06/2017
 */
@RestController
@RequestMapping("/api/repositories")
public class RepositoryController {
    private static final Logger LOG = LoggerFactory.getLogger(RepositoryController.class);
    @Autowired
    private RestClientService restClientService;

    /**
     * Proxy search api of github repositories as pagination of results.
     * Content-Type: application/json
     * 
     * @param query name of repository - mandatory parameter
     * @param page number of page - optional parameter(default: 0)
     * @param pageSize number items per page - optional parameter(default: 10,maximum = 100)
     * @param sortBy The sort field - optional parameter(stars, forks, or updated, Default: stars)
     * @param orderDirection direction of sort - optional parameter( asc or desc, default: desc)
     * @return list github repositories as json format
     */
    @RequestMapping(value = "/v1/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseList<Repository> searchRepositoriesAsJsonResponse(@RequestParam String query,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String sortBy, @RequestParam(required = false) String orderDirection) {
        return performSearch(query, page, pageSize, sortBy, orderDirection);
    }

    /**
     * Proxy search api of github repositories as pagination of results.
     * Content-Type: application/xml
     * 
     * @param query name of repository - mandatory parameter
     * @param page number of page - optional parameter(default: 0)
     * @param pageSize number items per page - optional parameter(default: 10,maximum = 100)
     * @param sortBy The sort field - optional parameter(stars, forks, or updated, Default: stars)
     * @param orderDirection direction of sort - optional parameter( asc or desc, default: desc)
     * @return list github repositories as xml format
     */
    @RequestMapping(value = "/v1/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE,
                    consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseList<Repository> searchRepositoriesAsXmlResponse(@RequestParam String query,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String sortBy, @RequestParam(required = false) String orderDirection) {
        return performSearch(query, page, pageSize, sortBy, orderDirection);
    }

    private ResponseList<Repository> performSearch(String query, int page, int pageSize, String sortBy, String orderDirection) {
        int validePageSize = Math.min(pageSize, 100);
        SearchRequestBuilder builder = SearchRequestBuilder.builder();
        builder.withQuery(query)//
                .withPageNumber(page)//
                .withPageSize(validePageSize)//
                .withSortField(sortBy)//
                .withOrderDirection(orderDirection);
        LOG.info(">>> Request parameters of search - [q={},page={},per_page={},sort={},order={}]", query, page, pageSize, sortBy,
                orderDirection);
        return restClientService.search(builder.build());
    }
}
