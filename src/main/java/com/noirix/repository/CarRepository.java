package com.noirix.repository;

import com.noirix.domain.Car;

import java.util.List;

public interface CarRepository extends CrudRepository<Long, Car>{
    @Override
    Car save(Car object);

    @Override
    List<Car> findAll();

    @Override
    Car findById(Long key);

    @Override
    Long delete(Car object);

}