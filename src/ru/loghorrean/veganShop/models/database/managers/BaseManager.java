package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.exceptions.DatabaseException;
import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract public class BaseManager<T extends DatabaseEntity, E extends DatabaseException> {
    protected final MySQLDatabase database;

    public BaseManager() throws SQLException {
        database = MySQLDatabase.getInstance();
    }

    abstract public List<T> getAll() throws SQLException, E;

    abstract public T getOne(int id) throws SQLException, E;

    abstract public void insert(T entity) throws SQLException, DatabaseException;

    abstract public void update(T entity) throws SQLException;

    abstract public void delete(T entity) throws SQLException;
}
