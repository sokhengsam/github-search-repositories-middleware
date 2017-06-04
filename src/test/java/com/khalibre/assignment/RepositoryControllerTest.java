package com.khalibre.assignment;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.khalibre.assignment.rest.RepositoryController;
import com.khalibre.assignment.services.RestClientService;

@RunWith(value = SpringRunner.class)
@WebMvcTest(value = RepositoryController.class, secure = false)
public class RepositoryControllerTest {

    private static final String SEARCH_KEYWORD = "github-search-repositories-middleware";
    private static final String REPOSITORIES_SEARCH_API = "/api/repositories/v1/search";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestClientService restClientService;

    @Test
    public void testSearchRepositoriesAsJsonResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get(REPOSITORIES_SEARCH_API).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("query", SEARCH_KEYWORD);
        MvcResult mvcResult = mockMvc.perform(builder)//
                .andDo(print())//
                .andExpect(status().isOk())//
                .andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testSearchRepositoriesAsXmlResponse() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get(REPOSITORIES_SEARCH_API).contentType(MediaType.APPLICATION_XML_VALUE)
                .param("query", SEARCH_KEYWORD);
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
