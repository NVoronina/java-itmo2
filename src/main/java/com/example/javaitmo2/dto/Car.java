package com.example.javaitmo2.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Car {
    private String vinNumber;
    private String brand;
    private Integer seatsCount;
    private ArrayList<Driver> drivers;

    public Car(String vinNumber, String brand, Integer seatsCount, ArrayList<Driver> drivers) {
        this.vinNumber = vinNumber;
        this.brand = brand;
        this.seatsCount = seatsCount;
        this.drivers = drivers;
    }
}
