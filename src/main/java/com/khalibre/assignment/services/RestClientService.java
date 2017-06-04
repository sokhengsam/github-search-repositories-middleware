package com.khalibre.assignment.services;

import com.khalibre.assignment.dto.Repository;
import com.khalibre.assignment.dto.ResponseList;
import com.khalibre.assignment.services.SearchRequestBuilder.SearchRequest;

/**
 * @author Sokheng SAM
 * @since 03/06/2017
 */
public interface RestClientService {
    ResponseList<Repository> search(SearchRequest searchRequest);
}
