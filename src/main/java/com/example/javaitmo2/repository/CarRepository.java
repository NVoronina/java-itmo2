package com.example.javaitmo2.repository;

import com.example.javaitmo2.dto.Car;
import com.example.javaitmo2.dto.Driver;

import java.util.ArrayList;

public class CarRepository {

    private ArrayList<Car> cars = new ArrayList<>();

    public ArrayList<Car> getList() {
        ArrayList<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("John", 29, "GGGGEROPP"));
        drivers.add(new Driver("Mary", 45, "FGHJJKKSL"));
        this.cars.add(new Car("RRR12TTFGHJS89", "mini", 4, drivers));
        this.cars.add(new Car("ZCR12TTFGHJS45", "bmw", 5, null));
        this.cars.add(new Car("YYY12TTFGHJS55", "lada", 8, drivers));

        return this.cars;
    }

    public Car getByVin(String vin) throws NotFoundException {
        this.getList();
        for (Car car: this.cars) {
            if (vin.equals(car.getVinNumber())) {
                return car;
            }
        }
        throw new NotFoundException("Car not found by vin " + vin);
    }

    public Car create(String vin, String brand, Integer seatCount) {
        Car car = new Car(vin, brand, seatCount, null);
        this.getList().add(car);

        return car;
    }

    public void delete(String vin) throws NotFoundException {
        Car car = this.getByVin(vin);
        this.getList().remove(car);
    }

    public Car update(Car car) throws NotFoundException {
        this.getList();
        for (Car existedCar: this.cars) {
            if (car.getVinNumber().equals(existedCar.getVinNumber())) {
                this.cars.remove(existedCar);
                this.cars.add(car);
                return car;
            }
        }
        throw new NotFoundException("Car not found by vin " + car.getVinNumber());
    }
}
