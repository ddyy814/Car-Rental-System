package com.example.demo_car_rental.service;

import com.example.demo_car_rental.model.Rent;

import java.util.List;


public interface RentService {

    List<Rent> findAll();
    
    Rent findByRentId(int id);

    Rent findByCustomerId(Long id);

    Rent findByCarId(Long id);

    Rent save(Rent rent);
}
