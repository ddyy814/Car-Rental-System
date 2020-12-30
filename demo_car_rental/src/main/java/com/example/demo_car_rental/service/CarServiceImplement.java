package com.example.demo_car_rental.service;

import com.example.demo_car_rental.model.Car;
import com.example.demo_car_rental.repository.CarDAO;
import com.example.demo_car_rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImplement implements CarService {

	@Autowired
    private CarDAO carDAO;


    public CarServiceImplement(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public void save(Car car) {
        this.carDAO.save(car);
    }

    @Override
    public List<Car> findAll() {
        return (List<Car>) carDAO.findAll();
    }

    @Override
    public Car findById(Long id) {
        return this.carDAO.findById(id);
    }
}
