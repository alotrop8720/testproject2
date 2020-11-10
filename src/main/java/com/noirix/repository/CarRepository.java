package com.noirix.repository;

import com.noirix.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Long, Car> {
    @Override
    default Car save(Car object) {
        return null;
    }

    @Override
    default List<Car> findAll() {
        return null;
    }

    @Override
    default Car findById(Long key) {
        return null;
    }

    @Override
    default Optional<Car> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    default Car update(Car object) {
        return null;
    }

    @Override
    default Long delete(Car object) {
        return null;
    }
}
