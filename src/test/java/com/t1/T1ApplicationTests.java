package com.t1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class T1ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    void countController() throws Exception {
        String expected = "{\"a\":2,\"b\":1}";
        MockHttpServletResponse response = mvc.perform(
                        get("/count?text=aab")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(200,response.getStatus());
        Assertions.assertEquals(expected,response.getContentAsString());
    }
    @Test
    void countControllerBadRequest() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get("/count?text=")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(400,response.getStatus());
    }

}
