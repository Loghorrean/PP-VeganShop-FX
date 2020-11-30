package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductsManager extends BaseManager<Product> {
    public ProductsManager() throws SQLException {
        super();
    }

    @Override
    public List<Product> getAll() throws SQLException {
        return null;
    }

    @Override
    public Product getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Product entity) throws SQLException {

    }

    @Override
    public void update(Product entity) throws SQLException {

    }

    @Override
    public void delete(Product entity) throws SQLException {

    }
}
