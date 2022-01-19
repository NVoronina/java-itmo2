package com.example.javaitmo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.javaitmo2.dto.Token;
import com.example.javaitmo2.dto.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.lang.Assert;
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

@SpringBootTest
@AutoConfigureMockMvc
class JavaItmo2ApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("lol");
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void shouldReturnedUnauthorizedCode() throws Exception {
		this.mockMvc.perform(get("/pwd/secret")).andDo(print()).andExpect(status().is(401));
	}

//	@Test
//	public void shouldReturnedSuccessCode() throws Exception {
//		MockHttpServletRequestBuilder requestBuilder = get("/auth/login");
//		ResultActions perform = this.mockMvc.perform(requestBuilder);
//		MvcResult mvcResult = perform.andReturn();
//		MockHttpServletResponse response = mvcResult.getResponse();
//		String content = response.getContentAsString();
//		Token token = objectMapper.readValue(content, Token.class);
//		Assert.hasText(token.getToken());
//	}

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
		Token token = objectMapper.readValue(content, Token.class);

		assertTrue(!token.getToken().isEmpty());
	}
}
