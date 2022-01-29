package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.CarRequest;
import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.dto.response.ErrorResponse;
import com.example.javaitmo2.dto.response.ResponseInterface;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.repository.ValidationException;
import com.example.javaitmo2.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("cars")
public class CarController {

    private CarService carService;
    private ModelMapper modelMapper;

    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<ArrayList<CarResponse>> getCarsList() {
        return ResponseEntity.ok().body(carService.getList());
    }

    @GetMapping("/{vinNumber}")
    public ResponseEntity<ResponseInterface> getCar(@PathVariable String vinNumber) {
        try {
            return ResponseEntity.ok().body(carService.getByVin(vinNumber));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseInterface> createCar(@RequestBody CarRequest car) {
        try {
            CarResponse carR = carService.create(car);
            System.out.println(carR.toString());
            return ResponseEntity.ok().body(carR);
        } catch (ValidationException e) {
            return ResponseEntity.status(400).body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping ("/{vinNumber}")
    public ResponseEntity<ResponseInterface> deleteCar(@PathVariable String vinNumber) {
        try {
            carService.delete(vinNumber);
            return null;
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ResponseInterface> updateCar(@RequestBody CarRequest car) {
        try {
            return ResponseEntity.ok().body(carService.update(car));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }
}
