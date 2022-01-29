package com.example.javaitmo2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverRequest {
    private Integer age;
    private String licenseNumber;
}
