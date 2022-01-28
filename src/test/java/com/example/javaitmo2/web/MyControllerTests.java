package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.response.TokenResponse;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnedUnauthorizedCode() throws Exception {
        this.mockMvc.perform(get("/pwd/secret")).andDo(print()).andExpect(status().is(401));
    }

//    @Test
//    public void shouldReturnedSuccessCode() throws Exception {
//        UserRequest request = new UserRequest("test@mail.test", "rrrrrrr");
//        MockHttpServletRequestBuilder requestBuilder = post("/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request));
//        ResultActions perform = this.mockMvc.perform(requestBuilder);
//        MvcResult mvcResult = perform.andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        String content = response.getContentAsString();
//        TokenResponse token = objectMapper.readValue(content, TokenResponse.class);
//
//        this.mockMvc.perform(get("/pwd/secret")
//                .header("Authorization", "Bearer " + token.getToken()))
//                .andDo(print())
//                .andExpect(status().is(200));
//
//    }

}
