package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.City;
import ru.loghorrean.veganShop.models.database.managers.CitiesManager;

import java.sql.SQLException;
import java.util.List;

public class ProfileData {
    private static ProfileData instance;
    private static CitiesManager citiesManager;
    private static List<City> availableCities;

    private ProfileData() throws SQLException {
        citiesManager = new CitiesManager();
        setCities();
    }

    public static ProfileData getInstance() throws SQLException {
        if (instance == null) {
            instance = new ProfileData();
        }
        return instance;
    }

    public void setCities() throws SQLException {
        availableCities = citiesManager.getAll();
    }

    public List<City> getCities() {
        return availableCities;
    }

    public City getCityByName(String name) {
        for(City city: availableCities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public City getCityById(int id) {
        for(City city: availableCities) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }
}
