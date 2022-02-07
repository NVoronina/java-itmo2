package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.request.CarRequest;
import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<CarResponse>> getCarsList() {
        return ResponseEntity.ok().body(carService.getList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer minSeatsCount,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size
    ) {
        return ResponseEntity.ok().body(carService.search(brand, minSeatsCount, page, size));
    }

    @GetMapping("/{vinNumber}")
    public ResponseEntity<CarResponse> getCar(@PathVariable String vinNumber) {
        return ResponseEntity.ok().body(carService.getByVin(vinNumber));
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest car) {
        CarResponse carR = carService.create(car);
        return ResponseEntity.ok().body(carR);
    }

    @DeleteMapping ("/{vinNumber}")
    public void deleteCar(@PathVariable String vinNumber) {
        carService.delete(vinNumber);
    }

    @PutMapping
    public ResponseEntity<CarResponse> updateCar(@RequestBody CarRequest car) {
        return ResponseEntity.ok().body(carService.update(car));
    }
}
