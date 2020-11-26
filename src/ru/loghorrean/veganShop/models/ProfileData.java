package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.CityEntity;
import ru.loghorrean.veganShop.models.database.managers.CitiesManager;

import java.sql.SQLException;
import java.util.List;

public class ProfileData {
    private static ProfileData instance;
    private static CitiesManager citiesManager;
    private static List<CityEntity> availableCities;

    private ProfileData() throws SQLException {
        citiesManager = CitiesManager.getInstance();
        setCities();
    }

    public static ProfileData getInstance() throws SQLException {
        if (instance == null) {
            instance = new ProfileData();
        }
        return instance;
    }

    public void setCities() throws SQLException {
        availableCities = citiesManager.getAllCities();
    }

    public CityEntity getCityByName(String name) {
        for(CityEntity city: availableCities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public CityEntity getCityById(int id) {
        for(CityEntity city: availableCities) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }
}
