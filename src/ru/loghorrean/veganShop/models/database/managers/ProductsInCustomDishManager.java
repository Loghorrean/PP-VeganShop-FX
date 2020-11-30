package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.ProductInCustomDish;

import java.sql.SQLException;
import java.util.List;

public class ProductsInCustomDishManager extends BaseManager<ProductInCustomDish> {
    public ProductsInCustomDishManager() throws SQLException {
        super();
    }

    @Override
    public List<ProductInCustomDish> getAll() throws SQLException {
        return null;
    }

    @Override
    public ProductInCustomDish getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(ProductInCustomDish entity) throws SQLException {

    }

    @Override
    public void update(ProductInCustomDish entity) throws SQLException {

    }

    @Override
    public void delete(ProductInCustomDish entity) throws SQLException {

    }
}
