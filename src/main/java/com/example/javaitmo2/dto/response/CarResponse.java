package com.example.javaitmo2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse implements ResponseInterface {
    private Integer id;
    private String vinNumber;
    private String brand;
    private Integer seatsCount;
    private ArrayList<DriverResponse> drivers;
}
