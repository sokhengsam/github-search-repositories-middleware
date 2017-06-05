package com.khalibre.assignment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.khalibre.assignment.dto.Repository;
import com.khalibre.assignment.dto.RepositoryResponseList;

@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryControllerTest {

    private static final String SEARCH_KEYWORD = "github-search-repositories-middleware";
    private static final String REPOSITORIES_SEARCH_API = "/api/repositories/v1/search?query={q}";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSearchRepositoriesAsJsonResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> headerEnitity = new HttpEntity<String>(headers);
        ResponseEntity<RepositoryResponseList> responseEntity = restTemplate.exchange(REPOSITORIES_SEARCH_API, HttpMethod.GET,
                headerEnitity, RepositoryResponseList.class, SEARCH_KEYWORD);
        RepositoryResponseList repositoryResponseList = responseEntity.getBody();
        Repository repository = repositoryResponseList.getItems().get(0);
        Assert.assertEquals(1L, repositoryResponseList.getTotalCount().longValue());
        Assert.assertEquals(SEARCH_KEYWORD, repository.getName());
    }

    @Test
    public void testSearchRepositoriesAsXmlResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> headerEnitity = new HttpEntity<String>(headers);
        ResponseEntity<RepositoryResponseList> responseEntity = restTemplate.exchange(REPOSITORIES_SEARCH_API, HttpMethod.GET,
                headerEnitity, RepositoryResponseList.class, SEARCH_KEYWORD);
        RepositoryResponseList repositoryResponseList = responseEntity.getBody();
        Repository repository = repositoryResponseList.getItems().get(0);
        Assert.assertEquals(1L, repositoryResponseList.getTotalCount().longValue());
        Assert.assertEquals(SEARCH_KEYWORD, repository.getName());
    }
}
