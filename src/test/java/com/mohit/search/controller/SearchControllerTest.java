package com.mohit.search.controller;

import com.mohit.search.SearchApplication;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebAppConfiguration
@SpringBootTest
public class SearchControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchApplication.class);

    @BeforeTest
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenBaseURI_whenMockMVC_thenResponseOK() throws Exception{
        try{
            this.mockMvc.perform(get("/searchengine")).andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("[]"));
        }catch(Exception ex){
            LOGGER.error(ex.toString());
        }
    }

    @Test
    public void givenBaseURIWithBadBooleanQueryParameter_thenResponseBadRequest() throws Exception{
        try{
            this.mockMvc.perform(get("/searchengine")
                    .param("inStock", "test")).andDo(print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
        }catch(Exception ex){
            LOGGER.error(ex.toString());
        }
    }

    @Test
    public void givenBaseURIWithBadMinRatingQueryParameter_thenResponseBadRequest() throws Exception{
        try{
            this.mockMvc.perform(get("/searchengine")
                    .param("minReviewRating", "10")).andDo(print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
        }catch(Exception ex){
            LOGGER.error(ex.toString());
        }
    }

    @Test
    public void givenBaseURIWithBadMaxRatingQueryParameter_thenResponseBadRequest() throws Exception{
        try{
            this.mockMvc.perform(get("/searchengine")
                    .param("maxReviewRating", "-1")).andDo(print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
        }catch(Exception ex){
            LOGGER.error(ex.toString());
        }
    }

    @Test
    public void givenBaseURIWithBadMinRatingCountQueryParameter_thenResponseBadRequest() throws Exception{
        try{
            this.mockMvc.perform(get("/searchengine")
                    .param("minReviewCount", "-1")).andDo(print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
        }catch(Exception ex){
            LOGGER.error(ex.toString());
        }
    }

    @Test
    public void givenBaseURIWithBadMaxRatingCountQueryParameter_thenResponseBadRequest() throws Exception{
        try{
            this.mockMvc.perform(get("/searchengine")
                    .param("maxReviewCount", "-100")).andDo(print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
        }catch(Exception ex){
            LOGGER.error(ex.toString());
        }
    }

}