package com.noirix.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;

    private String model;
    private int creatingYear;
    private User user;
    private int price;
    private String color;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", creatingYear=" + creatingYear +
                ", user=" + user +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
