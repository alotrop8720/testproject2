package com.noirix.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*Here we will store PK of m_users table*/
    private Long id;

    private String name;

    private String surname;

    private Date birthDate;

    private Gender gender = Gender.NOT_SELECTED;

    private Timestamp created;

    private Timestamp changed;

    private Float weight;
}
