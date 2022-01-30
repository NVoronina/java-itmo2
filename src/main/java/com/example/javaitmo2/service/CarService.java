package com.example.javaitmo2.service;

import com.example.javaitmo2.dto.request.CarRequest;
import com.example.javaitmo2.dto.request.DriverRequest;
import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.entity.CarEntity;
import com.example.javaitmo2.entity.DriverEntity;
import com.example.javaitmo2.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private CarRepository repository;
    private ModelMapper modelMapper;

    public CarService(CarRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ArrayList<CarResponse> getList() {
        List<CarEntity> list = repository.findAll();
        ArrayList<CarResponse> result = new ArrayList<>();
        for (CarEntity car: list) {
            result.add(modelMapper.map(car, CarResponse.class));
        }
        return result;
    }

    public CarResponse getByVin(String vin) throws NotFoundException {
        CarEntity car = repository.getByVinNumber(vin);
        if (car == null) {
            throw new NotFoundException("Car not found by vin " + vin);
        }
        return modelMapper.map(car, CarResponse.class);
    }

    public CarResponse create(CarRequest carRequest) {
        CarEntity car = repository.getByVinNumber(carRequest.getVinNumber());
        if (car != null) {
            throw new ValidationException("Car exist with vin " + carRequest.getVinNumber());
        }
        car = modelMapper.map(carRequest, CarEntity.class);
        repository.save(car);

        return modelMapper.map(car, CarResponse.class);
    }

    public void delete(String vin) throws NotFoundException {
        CarEntity car = repository.getByVinNumber(vin);
        if (car == null) {
            throw new NotFoundException("Car not found by vin " + vin);
        }
        repository.delete(car);
    }

    public CarResponse update(CarRequest car) throws NotFoundException {
        CarEntity carEntity = repository.getByVinNumber(car.getVinNumber());
        if (carEntity == null) {
            throw new NotFoundException("Car not found by vin " + car.getVinNumber());
        }
        carEntity.setBrand(car.getBrand());
        carEntity.setSeatsCount(car.getSeatsCount());

        for (DriverRequest driver: car.getDrivers()) {
            boolean updated = false;
            for (DriverEntity driverExist: carEntity.getDrivers()) {
                if (driver.getLicenseNumber().equals(driverExist.getLicenseNumber())) {
                    driverExist.setAge(driver.getAge());
                    updated = true;
                }
            }
            if (!updated) {
                carEntity.getDrivers().add(modelMapper.map(driver, DriverEntity.class));
            }
        }
        repository.save(carEntity);

        return modelMapper.map(carEntity, CarResponse.class);
    }
}
