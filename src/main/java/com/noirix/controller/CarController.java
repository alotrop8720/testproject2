package com.noirix.controller;

import com.noirix.controller.request.CarCreateRequest;
import com.noirix.domain.Car;
import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final String CAR_PAGE = "cars";
    private final String CARS_LIST_ATTRIBUTE = "cars";

    @PostMapping("/create")
    public ModelAndView createCar(@ModelAttribute CarCreateRequest carCreateRequest) {
        Car car = new Car();
        car.setModel(carCreateRequest.getModel());
        car.setCreationYear(carCreateRequest.getCreationYear());
        car.setUserId(carCreateRequest.getUserId());
        car.setPrice(carCreateRequest.getPrice());
        car.setColor(carCreateRequest.getColor());
        carService.save(car);

        ModelAndView result = new ModelAndView();
        result.setViewName(CAR_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, Collections.singletonList(carService.findAll()));
        return result;
    }

    @GetMapping
    public ModelAndView getAllCars() {
        ModelAndView result = new ModelAndView();

        result.setViewName(CAR_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, carService.findAll());
        return result;
    }

    @GetMapping(value = "/search/{id}")
    public ModelAndView search(@PathVariable("id") Long carId) {
        ModelAndView result = new ModelAndView();

        result.setViewName(CAR_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, Collections.singletonList(carService.findById(carId)));
        return result;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long carId) {
        ModelAndView result = new ModelAndView();

        result.setViewName(CAR_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, Collections.singletonList(carService.findById(carId)));
        return result;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateCar(@PathVariable("id") Long carId, @ModelAttribute CarCreateRequest carCreateRequest) {
        Car car = carService.findById(carId);
        car.setModel(carCreateRequest.getModel());
        car.setCreationYear(carCreateRequest.getCreationYear());
        car.setUserId(carCreateRequest.getUserId());
        car.setPrice(carCreateRequest.getPrice());
        car.setColor(carCreateRequest.getColor());
        carService.update(car);

        ModelAndView result = new ModelAndView();
        result.setViewName(CAR_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, Collections.singletonList(car));
        return result;
    }


}
