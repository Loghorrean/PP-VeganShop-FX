package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.City;
import ru.loghorrean.veganShop.models.database.managers.CitiesManager;
import ru.loghorrean.veganShop.models.database.managers.UsersManager;

import java.sql.SQLException;
import java.util.List;

public class ProfileData {
    private static ProfileData instance;
    private final CitiesManager citiesManager;
    private final UsersManager usersManager;
    private List<City> availableCities;

    private ProfileData() throws SQLException {
        citiesManager = new CitiesManager();
        usersManager = new UsersManager();
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

    public CitiesManager getCitiesManager() {
        return citiesManager;
    }

    public UsersManager getUsersManager() {
        return usersManager;
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
