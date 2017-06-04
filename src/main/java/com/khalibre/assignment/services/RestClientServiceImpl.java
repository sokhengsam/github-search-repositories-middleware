package com.khalibre.assignment.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.khalibre.assignment.dto.Repository;
import com.khalibre.assignment.dto.RepositoryResponseList;
import com.khalibre.assignment.dto.ResponseList;
import com.khalibre.assignment.services.SearchRequestBuilder.SearchRequest;

/**
 * @author Sokheng SAM
 * @since 03/06/2017
 */
@Service
public class RestClientServiceImpl implements RestClientService {
    private static final Logger LOG = LoggerFactory.getLogger(RestClientServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    private String url = "https://api.github.com/search/repositories";

    @Override
    @CachePut("repositories")
    public ResponseList<Repository> search(SearchRequest searchRequest) {
        LOG.info(">>> Execute request to github search repositories api.");
        return restTemplate.getForObject(searchRequest.toUri(url), RepositoryResponseList.class);
    }
}
