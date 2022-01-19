package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.Car;
import com.example.javaitmo2.repository.CarRepository;
import com.example.javaitmo2.repository.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("cars")
public class CarController {

    @GetMapping("/list")
    public ResponseEntity<ArrayList<Car>> getCarsList() {
        return ResponseEntity.ok().body(new CarRepository().getList());
    }

    @GetMapping("/{vinNumber}")
    public ResponseEntity<Car> getCar(@PathVariable String vinNumber) {
        try {
            return ResponseEntity.ok().body(new CarRepository().getByVin(vinNumber));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            ResponseEntity.notFound();
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok().body(
                new CarRepository()
                        .create(car.getVinNumber(), car.getBrand(), car.getSeatsCount()));
    }

    @DeleteMapping ("/{vinNumber}")
    public void deleteCar(@PathVariable String vinNumber) {
        try {
            new CarRepository().delete(vinNumber);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            ResponseEntity.notFound();
        }
    }

    @PutMapping
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        try {
            return ResponseEntity.ok().body(new CarRepository().update(car));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            ResponseEntity.notFound();
            return null;
        }
    }
}
