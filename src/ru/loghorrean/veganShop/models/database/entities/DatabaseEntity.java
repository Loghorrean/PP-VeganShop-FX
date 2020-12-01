package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

abstract public class DatabaseEntity {
    protected int id = -1;

    public DatabaseEntity(int id) throws DatabaseException {
        setId(id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) throws DatabaseException  {
        if (id != -1 && id < 0) {
            throw new DatabaseException("Wrong id of the database");
        }
        this.id = id;
    }
}
