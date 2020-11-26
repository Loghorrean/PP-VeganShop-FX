package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.CityEntity;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitiesManager {
    private MySQLDatabase database;
    private static CitiesManager instance;

    private CitiesManager(MySQLDatabase database) {
        this.database = database;
    }

    public static CitiesManager getInstance() {
        if (instance == null) {
            instance = new CitiesManager(MySQLDatabase.getInstance());
        }
        return instance;
    }

    public List<CityEntity> getAllCities() throws SQLException {
        try(Connection c = database.getConnection()) {
            Statement s = c.createStatement();
            String sql = "SELECT * FROM cities";
            ResultSet set = s.executeQuery(sql);
            ArrayList<CityEntity> cities = new ArrayList<>();
            while (set.next()) {
                cities.add(new CityEntity(
                        set.getInt("city_id"),
                        set.getString("city_name")
                ));
            }
            return cities;
        }
    }
}
