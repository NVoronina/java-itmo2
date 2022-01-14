package com.example.javaitmo2.dto;

import lombok.Data;

@Data
public class Driver {
    private String name;
    private Integer age;
    private String licenseNumber;

    public Driver(String name, Integer age, String licenseNumber) {
        this.name = name;
        this.age = age;
        this.licenseNumber = licenseNumber;
    }
}
