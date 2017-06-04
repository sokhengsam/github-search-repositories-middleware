package com.khalibre.assignment.services;

import java.io.Serializable;
import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchRequestBuilder implements Serializable{
    private static final long serialVersionUID = 3443737680181043585L;
    private String query;
    private int page;
    private int perPage;
    private String sortField;
    private String orderDirection;

    public static SearchRequestBuilder builder() {
        return new SearchRequestBuilder();
    }

    public SearchRequestBuilder withQuery(String query) {
        this.query = query;
        return this;
    }

    public SearchRequestBuilder withPageNumber(int pageNumber) {
        this.page = pageNumber;
        return this;
    }

    public SearchRequestBuilder withPageSize(int pageSize) {
        this.perPage = pageSize;
        return this;
    }

    public SearchRequestBuilder withSortField(String sortField) {
        this.sortField = sortField;
        return this;
    }

    public SearchRequestBuilder withOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
        return this;
    }

    public SearchRequest build() {
        return new SearchRequest(query, page, perPage, sortField, orderDirection);
    }

    protected class SearchRequest implements Serializable{
        private static final long serialVersionUID = 6582138475782028346L;
        private String q;
        private int page;
        private int perPage;
        private String sort;
        private String order;

        private SearchRequest(String q, int page, int perPage, String sort, String order) {
            this.q = q;
            this.page = page;
            this.perPage = perPage;
            this.sort = sort;
            this.order = order;
        }

        public URI toUri(String url) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            builder.queryParam("q", q)//
                    .queryParam("page", page)//
                    .queryParam("per_page", perPage == 0 ? 10 : perPage)//
                    .queryParam("order", order == null ? "desc" : order);
            if (sort != null) {
                builder.queryParam("sort", sort);
            }
            return builder.build().toUri();
        }
    }
}
