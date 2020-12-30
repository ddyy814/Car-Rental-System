package com.example.demo_car_rental.service;

import com.example.demo_car_rental.model.Car;
import java.util.List;


public interface CarService {
    List<Car> findAll();

    Car findById(Long id);

    void save(Car car);
}
