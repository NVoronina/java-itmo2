package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.Car;
import com.example.javaitmo2.repository.CarRepository;
import com.example.javaitmo2.repository.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("cars")
public class CarController {

    @GetMapping("/list")
    public ArrayList<Car> getCarsList() {
        return new CarRepository().getList();
    }

    @GetMapping("/{vinNumber}")
    public Car getCar(@PathVariable String vinNumber) {
        try {
            return new CarRepository().getByVin(vinNumber);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return new CarRepository().create(car.getVinNumber(), car.getBrand(), car.getSeatsCount());
    }

    @DeleteMapping ("/{vinNumber}")
    public void deleteCar(@PathVariable String vinNumber) {
        try {
            new CarRepository().delete(vinNumber);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car) {
        try {
            return new CarRepository().update(car);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
