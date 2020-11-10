package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private String model;

    private Integer creationYear;

    private Double price;

    private Long userId;

    private String color;
}
