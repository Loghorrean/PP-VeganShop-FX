package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.CityException;
import ru.loghorrean.veganShop.exceptions.DatabaseException;

public class City extends DatabaseEntity {
    private String name;

    public City(int id, String name) throws DatabaseException {
        super(id);
        setName(name);
    }

    public City(String name) throws DatabaseException {
        this(-1, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws CityException {
        if (name.length() < 1 || name.length() > 25) {
            throw new CityException("Название города должно быть не менее одного и не более 25 символов");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
