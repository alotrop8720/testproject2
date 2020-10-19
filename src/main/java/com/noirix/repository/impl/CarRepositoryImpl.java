package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.domain.Gender;
import com.noirix.domain.User;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.repository.CarRepository;
import com.noirix.util.DatabasePropertiesReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.noirix.util.DatabasePropertiesReader.*;

public class CarRepositoryImpl implements CarRepository{
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();
    private static Logger logger = Logger.getLogger(CarRepositoryImpl.class.getName());

    private static final String ID = "id";
    private static final String MODEL = "model";
    private static final String CREATIONYEAR = "creation_year";
    private static final String USER = "user_id";
    private static final String PRICE = "price";
    private static final String COLOR = "color";


    public List<Car> search(String query) {
        List<Car> cars = new ArrayList<>();

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            logger.info("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = DriverManager.getConnection(
                    reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                cars.add(parseResultSet(rs));
            }
            return cars;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Car save(Car car) {
        final String query = "insert into m_cars(id, model, creation_year, user_id, price, color) " +
                "values(?,?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;

        try{
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        }catch (ClassNotFoundException e){
            logger.info("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(
                    reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(query);
            PreparedStatement lastInsertId = connection
                    .prepareStatement("SELECT currval('m_cars_id_seq') as last_insert_id;");

            statement.setString(1, car.getModel());
            statement.setInt(2, car.getCreatingYear());
            statement.setLong(3, car.getUser().getId());
            statement.setInt(4, car.getPrice());
            statement.setString(5, car.getColor());

            statement.execute();
            Long insertedId;
            ResultSet lastIdResultSet = lastInsertId.executeQuery();
            if (lastIdResultSet.next()) {
                insertedId = lastIdResultSet.getLong("last_insert_id");
            } else {
                throw new RuntimeException("We cannot read sequence last value during User creation!");
            }
            return findById(insertedId);
        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }

    }

    private Car parseResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong(ID));
        car.setModel(rs.getString(MODEL));
        car.setCreatingYear(rs.getInt(CREATIONYEAR));
        car.setUser(new UserRepositoryImpl().findById(rs.getLong(USER)));
        car.setPrice(rs.getInt(PRICE));
        car.setColor(rs.getString(COLOR));
        return car;
    }

    @Override
    public List<Car> findAll() {
        final String query = "Select * from m_cars order by id";

        List<Car> cars = new ArrayList<>();

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            logger.info("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = DriverManager.getConnection(
                    reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                cars.add(parseResultSet(rs));
            }
            return cars;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Car findById(Long key) {
        final String query = "select * from m_cars where id=?";

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        try{
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        }catch (ClassNotFoundException e){
            logger.info("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(
                    reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(query);
            statement.setLong(1, key);

            rs = statement.executeQuery();

            if (rs.next()) {
                return parseResultSet(rs);
            } else {
                throw new EntityNotFoundException("User with ID:" + key + "not found");
            }
        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }

    }

    @Override
    public Long delete(Car car) {
        final String query = "delete from m_cars where id = ?";

        Connection connection;
        PreparedStatement statement;

        try{
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        }catch (ClassNotFoundException e){
            logger.info("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(
                    reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(query);
            statement.setLong(1, car.getId());

            int deletedRows = statement.executeUpdate();
            return (long)deletedRows;
        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Optional<Car> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Car update(Car car) {
        final String query = "update m_cars " +
                "set " +
                "model = ?  " +
                "creation_year = ?,  " +
                "user_id = ?,  " +
                "price = ?,  " +
                "color = ?  ";


        Connection connection;
        PreparedStatement statement;

        try{
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        }catch (ClassNotFoundException e){
            logger.info("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(
                    reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(query);

            statement.setString(1, car.getModel());
            statement.setInt(2, car.getCreatingYear());
            statement.setLong(3, car.getUser().getId());
            statement.setInt(4, car.getPrice());
            statement.setString(5, car.getColor());

            statement.executeUpdate();
            return findById(car.getId());
        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }
}
