package com.example.demo_car_rental.repository;

import com.example.demo_car_rental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.lang.Long;
import java.util.List;

@Repository
public interface CarDAO extends JpaRepository<Car, Integer> {
    List<Car> findAll();
    Car findById(Long id);
}
