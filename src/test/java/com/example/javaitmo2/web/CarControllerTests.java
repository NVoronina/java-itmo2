package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.DriverRequest;
import com.example.javaitmo2.dto.request.OwnerRequest;
import com.example.javaitmo2.dto.request.UserRequest;
import com.example.javaitmo2.dto.request.CarRequest;
import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.dto.response.ErrorResponse;
import com.example.javaitmo2.dto.response.TokenResponse;
import com.example.javaitmo2.entity.OwnerEntity;
import com.example.javaitmo2.service.CarService;
import com.example.javaitmo2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTests {

    private static String token;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @BeforeEach
    public void setUpClass() throws Exception {
        if (token != null) {
            return;
        }
        UserRequest request = new UserRequest("test@mail.test", "rrrrrrr", "Natalia", "Voronina");
        userService.addUser(request);
        MockHttpServletRequestBuilder requestBuilder = post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));
        ResultActions perform = this.mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        TokenResponse tokenResponse = objectMapper.readValue(content, TokenResponse.class);
        token = tokenResponse.getToken();
    }

    @Test
    public void shouldReturnedSuccessGetList() throws Exception {
        ResultActions perform = this.mockMvc.perform(get("/cars/list")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is(200));
        MvcResult mvcResult = perform.andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        assertFalse(content.isEmpty());
    }

    @Test
    public void shouldReturnedSuccessGetOne() throws Exception {
        OwnerRequest ownerRequest = new OwnerRequest("DOC_CAR_REG_2", new Date());
        String expectedVin = "RRR12TTFGHJS89";
        CarRequest request = new CarRequest(expectedVin, "toyota", 5, null, ownerRequest);
        carService.create(request);

        ResultActions perform = this.mockMvc.perform(get("/cars/" + expectedVin)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is(200));
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        CarResponse car = objectMapper.readValue(content, CarResponse.class);

        assertEquals(expectedVin, car.getVinNumber());
    }

    @Test
    public void shouldReturnedExceptionGetOne() throws Exception {
        this.mockMvc.perform(get("/cars/AAAAA")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    public void shouldReturnedSuccessPost() throws Exception {
        ArrayList<DriverRequest> driverRequests = new ArrayList<DriverRequest>();
        driverRequests.add(new DriverRequest(44, "lisencenumber"));

        OwnerRequest ownerRequest = new OwnerRequest("DOC_CAR_REG_1", new Date());
        driverRequests.add(new DriverRequest(44, "lisencenumber"));

        CarRequest request = new CarRequest("RETSBHGDG", "bmw", 4, driverRequests, ownerRequest);

        MockHttpServletRequestBuilder requestBuilder = post("/cars")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));
        ResultActions perform = this.mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        CarResponse responseCar = objectMapper.readValue(content, CarResponse.class);

        assertEquals(request.getBrand(), responseCar.getBrand());
        assertEquals(request.getVinNumber(), responseCar.getVinNumber());
        assertEquals(request.getSeatsCount(), responseCar.getSeatsCount());
    }

    @Test
    public void shouldReturnedSuccessPut() throws Exception {
        OwnerRequest ownerRequest = new OwnerRequest("DOC_CAR_REG_2", new Date());

        CarRequest request = new CarRequest("ZCR12TTFGHJS45", "toyota", 5, new ArrayList<>(), ownerRequest);
        carService.create(request);

        MockHttpServletRequestBuilder requestBuilder = put("/cars")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        ResultActions perform = this.mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        CarResponse responseCar = objectMapper.readValue(content, CarResponse.class);

        assertEquals(request.getBrand(), responseCar.getBrand());
        assertEquals(request.getVinNumber(), responseCar.getVinNumber());
        assertEquals(request.getSeatsCount(), responseCar.getSeatsCount());
    }

    @Test
    public void shouldReturnedPutException() throws Exception {
        OwnerRequest ownerRequest = new OwnerRequest("DOC_CAR_REG_2", new Date());

        CarRequest request = new CarRequest("HHHGHHHHH", "toyota", 5, null, ownerRequest);
        MockHttpServletRequestBuilder requestBuilder = put("/cars")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));
        ResultActions perform = this.mockMvc.perform(requestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        ErrorResponse responseError = objectMapper.readValue(content, ErrorResponse.class);

        assertFalse(responseError.getMessage().isEmpty());
        assertEquals(response.getStatus(), 404);

    }

    @Test
    public void shouldReturnedSuccessDelete() throws Exception {
        OwnerRequest ownerRequest = new OwnerRequest("DOC_CAR_REG_2", new Date());

        CarRequest request = new CarRequest("YYY12TTFGHJS55", "toyota", 5, null, ownerRequest);

        carService.create(request);
        this.mockMvc.perform(delete("/cars/YYY12TTFGHJS55")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void shouldReturnedDeleteException() throws Exception {
        this.mockMvc.perform(delete("/cars/dddddd")
                        .header("Authorization", "Bearer " + this.token))
                .andDo(print())
                .andExpect(status().is(404));
    }
}
