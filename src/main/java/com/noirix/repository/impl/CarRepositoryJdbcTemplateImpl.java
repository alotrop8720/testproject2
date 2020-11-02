package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.repository.CarColumns;
import com.noirix.repository.CarRepository;
import com.noirix.repository.UserColumns;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Primary
public class CarRepositoryJdbcTemplateImpl implements CarRepository {
    private static Logger log = Logger.getLogger(CarRepositoryJdbcTemplateImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Car save(Car car) {
        final String query = "insert into m_cars(model, creation_year, user_id, price, color) " +
                "values (:model, :creation_year, :user_id, :price, :color);";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("model", car.getModel());
        params.addValue("creation_year", car.getCreationYear());
        params.addValue("user_id", car.getUserId());
        params.addValue("price", car.getPrice());
        params.addValue("color", car.getColor());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(query, params, keyHolder, new String[]{"id"});

        long createdCarId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdCarId);
    }

    private Car getCarRowMapper(ResultSet rs, int i) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong(CarColumns.ID));
        car.setModel(rs.getString(CarColumns.MODEL));
        car.setCreationYear(rs.getInt(CarColumns.CREATION_YEAR));
        car.setUserId(rs.getLong(CarColumns.USER_ID));
        car.setPrice(rs.getDouble(CarColumns.PRICE));
        car.setColor(rs.getString(CarColumns.COLOR));
        return car;
    }

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query("select * from m_cars", this::getCarRowMapper);
    }

    @Override
    public Car findById(Long key) {
        return jdbcTemplate.queryForObject("select * from m_cars where id = ?",
                new Object[]{key}, this::getCarRowMapper);
    }

    @Override
    public Car update(Car car) {
        final String query = "update m_cars set " +
                "model = ?, creation_year = ?, price = ?, user_id = ?, color = ?";
        jdbcTemplate.update(query, car.getModel(), car.getCreationYear(), car.getPrice(),
                car.getColor());
        return findById(car.getId());
    }

    @Override
    public Long delete(Car car) {
        jdbcTemplate.update("delete from m_cars where id = ?", car.getId());
        return car.getId();
    }
}
