package com.noirix.service;

import com.noirix.domain.Car;
import com.noirix.domain.User;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car save(Car car);

    Car findById(Long carId);

    Long delete(Car car);
}
