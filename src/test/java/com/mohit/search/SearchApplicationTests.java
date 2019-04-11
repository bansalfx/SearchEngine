package com.mohit.search;

import com.mohit.search.controller.SearchController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeTest;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SearchApplicationTests {

    @Autowired
    private SearchController controller;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchApplication.class);

    @BeforeTest
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void contextLoads() {
        Assert.assertNotNull(controller);
    }



}
