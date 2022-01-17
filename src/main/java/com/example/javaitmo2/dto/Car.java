package com.example.javaitmo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Car {
    private String vinNumber;
    private String brand;
    private Integer seatsCount;
    private ArrayList<Driver> drivers;
}
