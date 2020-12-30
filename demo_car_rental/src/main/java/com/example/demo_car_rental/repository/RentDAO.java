package com.example.demo_car_rental.repository;

import com.example.demo_car_rental.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentDAO extends JpaRepository<Rent, Long> {
	Rent findById(int id);
    Rent findByCustomerId(Long id);
    Rent findByCarId(Long id);
    List<Rent> findAll();

/*
    @Query("select NEW com.example.demo_car_rental.model.AvailableCar"+
            "(b.id, b.car.id, b.car.name, b.car.price)"+
            "from RentDate as b "+
            "where :startDate not between b.startDate and b.endDate "+
            "and :endDate not between b.startDate and b.endDate "+
            "and b.car.id NOT IN (select DISTINCT bd.car.id "+
            "from RentDate bd "+
            "where :startDate between bd.startDate and bd.endDate "+
            "OR :endDate between bd.startDate and bd.endDate) " +
            "group by b.car.id")
    List<AvailableCar> checkAvailableCars(@Param("startDate") Calendar startDate,
                                          @Param("endDate") Calendar endDate);

    @Query("select NEW com.example.demo_car_rental.model.AvailableCar "+
            "(b.id, b.car.id, b.car.name, b.car.price) "+
            "from RentDate as b "+
            "where :startDate not between b.startDate and b.endDate "+
            "and :endDate not between b.startDate and b.endDate "+
            "and b.car.id = :carId "+
            "and b.car.id NOT IN (select DISTINCT bd.car.id "+
            "from RentDate bd "+
            "where :startDate between bd.startDate and bd.endDate "+
            "OR :endDate between bd.startDate and bd.endDate)")
    List<AvailableCar> checkAvailableCarById(@Param("startDate") Calendar startDate,
                                                    @Param("endDate") Calendar endDate,
                                                    @Param("carId") Long id);*/
}
