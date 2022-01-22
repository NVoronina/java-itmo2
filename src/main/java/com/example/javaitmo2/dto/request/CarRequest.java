package com.example.javaitmo2.dto.request;

import com.example.javaitmo2.dto.Driver;
import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private String vinNumber;
    private String brand;
    private Integer seatsCount;
}
