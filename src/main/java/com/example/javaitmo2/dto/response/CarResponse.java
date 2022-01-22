package com.example.javaitmo2.dto.response;

import com.example.javaitmo2.dto.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CarResponse implements ResponseInterface {
    private String vinNumber;
    private String brand;
    private Integer seatsCount;
    private ArrayList<Driver> drivers;
}
