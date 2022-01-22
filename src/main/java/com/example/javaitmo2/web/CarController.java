package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.CarRequest;
import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.dto.response.ErrorResponse;
import com.example.javaitmo2.dto.response.ResponseInterface;
import com.example.javaitmo2.repository.CarRepository;
import com.example.javaitmo2.repository.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("cars")
public class CarController {

    @GetMapping("/list")
    public ResponseEntity<ArrayList<CarResponse>> getCarsList() {
        return ResponseEntity.ok().body(new CarRepository().getList());
    }

    @GetMapping("/{vinNumber}")
    public ResponseEntity<ResponseInterface> getCar(@PathVariable String vinNumber) {
        try {
            return ResponseEntity.ok().body(new CarRepository().getByVin(vinNumber));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseInterface> createCar(@RequestBody CarRequest car) {
        return ResponseEntity.ok().body(
                new CarRepository()
                        .create(car.getVinNumber(), car.getBrand(), car.getSeatsCount()));
    }

    @DeleteMapping ("/{vinNumber}")
    public ResponseEntity<ResponseInterface> deleteCar(@PathVariable String vinNumber) {
        try {
            new CarRepository().delete(vinNumber);
            return null;
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ResponseInterface> updateCar(@RequestBody CarRequest car) {
        try {
            return ResponseEntity.ok().body(new CarRepository().update(car));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }
}
