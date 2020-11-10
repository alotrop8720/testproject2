package com.noirix.controller.request;

import lombok.Data;

@Data
public class CarCreateRequest {
    private String model;

    private Integer creationYear;

    private Double price;

    private Long userId;

    private String color;

}
