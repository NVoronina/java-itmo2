package com.example.javaitmo2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse implements ResponseInterface {
    private Integer id;
    private Integer age;
    private String licenseNumber;
}
