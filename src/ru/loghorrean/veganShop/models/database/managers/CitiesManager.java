package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitiesManager extends BaseManager<City> {
    public CitiesManager() {
        super();
    }

    @Override
    public List<City> getAll() throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "SELECT * FROM cities";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<City> cities = new ArrayList<>();
            while (set.next()) {
                cities.add(new City(
                        set.getInt("city_id"),
                        set.getString("city_name")
                ));
            }
            return cities;
        }
    }

    @Override
    public City getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM cities WHERE city_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new City(
                        set.getInt("city_id"),
                        set.getString("city_name")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(City city) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "INSERT INTO cities (city_name) VALUES (?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, city.getName());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                city.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE ADDING CITY " + city.getName());
        }
    }

    @Override
    public void update(City city) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE cities SET city_name = ? WHERE city_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, city.getName());
            s.setInt(2, city.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING A CITY " + city.getName());
        }
    }

    @Override
    public void delete(City city) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM cities WHERE city_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, city.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING A CITY " + city.getName());
        }
    }
}
