package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;

import java.sql.SQLException;
import java.util.List;

abstract public class BaseManager<T extends DatabaseEntity> {
    protected final MySQLDatabase database;

    public BaseManager() throws SQLException {
        database = MySQLDatabase.getInstance();
    }

    abstract public List<T> getAll() throws SQLException;

    abstract public T getOne(int id) throws SQLException;

    abstract public void insert(T entity) throws SQLException;

    abstract public void update(T entity) throws SQLException;

    abstract public void delete(T entity) throws SQLException;
}
