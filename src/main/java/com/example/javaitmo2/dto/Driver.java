package com.example.javaitmo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Driver {
    private String name;
    private Integer age;
    private String licenseNumber;
}
