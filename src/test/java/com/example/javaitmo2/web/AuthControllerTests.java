package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.response.ErrorResponse;
import com.example.javaitmo2.dto.response.TokenResponse;
import com.example.javaitmo2.dto.request.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnedSuccessPost() throws Exception {
        UserRequest request = new UserRequest("test@mail.test", "rrrrrrr");
        MockHttpServletRequestBuilder requestBuilder = post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));
        ResultActions perform = this.mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        TokenResponse token = objectMapper.readValue(content, TokenResponse.class);
        assertFalse(token.getToken().isEmpty());
    }

    @Test
    public void shouldReturnedFailPost() throws Exception {
        UserRequest request = new UserRequest("test@mail.test", "dd");
        MockHttpServletRequestBuilder requestBuilder = post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));
        ResultActions perform = this.mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(content, ErrorResponse.class);

        assertEquals(error.getMessage(), "User not found by login and password");
        assertEquals(404, response.getStatus());
    }
}
