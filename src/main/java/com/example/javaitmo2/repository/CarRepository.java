package com.example.javaitmo2.repository;

import com.example.javaitmo2.dto.request.CarRequest;
import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.dto.Driver;
import com.example.javaitmo2.entity.EntityInterface;

import java.util.ArrayList;

public class CarRepository {

    private ArrayList<CarResponse> cars = new ArrayList<>();

    public ArrayList<CarResponse> getList() {
        ArrayList<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("John", 29, "GGGGEROPP"));
        drivers.add(new Driver("Mary", 45, "FGHJJKKSL"));
        this.cars.add(new CarResponse("RRR12TTFGHJS89", "mini", 4, drivers));
        this.cars.add(new CarResponse("ZCR12TTFGHJS45", "bmw", 5, null));
        this.cars.add(new CarResponse("YYY12TTFGHJS55", "lada", 8, drivers));

        return this.cars;
    }

    public CarResponse getByVin(String vin) throws NotFoundException {
        this.getList();
        for (CarResponse car: this.cars) {
            if (vin.equals(car.getVinNumber())) {
                return car;
            }
        }
        throw new NotFoundException("Car not found by vin " + vin);
    }

    public CarResponse create(String vin, String brand, Integer seatCount) {
        CarResponse car = new CarResponse(vin, brand, seatCount, null);
        this.getList().add(car);

        return car;
    }

    public void delete(String vin) throws NotFoundException {
        CarResponse car = this.getByVin(vin);
        this.getList().remove(car);
    }

    public CarResponse update(CarRequest car) throws NotFoundException {
        this.getList();
        for (CarResponse existedCar: this.cars) {
            if (car.getVinNumber().equals(existedCar.getVinNumber())) {
                existedCar.setBrand(car.getBrand());
                existedCar.setSeatsCount(car.getSeatsCount());

                return existedCar;
            }
        }
        throw new NotFoundException("Car not found by vin " + car.getVinNumber());
    }
}
