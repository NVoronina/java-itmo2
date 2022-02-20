package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.CarRequest;
import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.service.CarService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;
    private ModelMapper modelMapper;

    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Tag(name = "Car", description = "Cars CRUD operations")
    @GetMapping("/list")
    public ResponseEntity<List<CarResponse>> getCarsList() {
        return ResponseEntity.ok().body(carService.getList());
    }

    @Tag(name = "Car", description = "Cars CRUD operations")
    @GetMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer minSeatsCount,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size
    ) {
        return ResponseEntity.ok().body(carService.search(brand, minSeatsCount, page, size));
    }

    @Tag(name = "Car", description = "Cars CRUD operations")
    @GetMapping("/{vinNumber}")
    public ResponseEntity<CarResponse> getCar(@PathVariable String vinNumber) {
        return ResponseEntity.ok().body(carService.getByVin(vinNumber));
    }

    @Tag(name = "Car", description = "Cars CRUD operations")
    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest car) {
        CarResponse carR = carService.create(car);
        return ResponseEntity.ok().body(carR);
    }

    @Tag(name = "Car", description = "Cars CRUD operations")
    @DeleteMapping ("/{vinNumber}")
    public void deleteCar(@PathVariable String vinNumber) {
        carService.delete(vinNumber);
    }

    @Tag(name = "Car", description = "Cars CRUD operations")
    @PutMapping
    public ResponseEntity<CarResponse> updateCar(@RequestBody CarRequest car) {
        return ResponseEntity.ok().body(carService.update(car));
    }
}
