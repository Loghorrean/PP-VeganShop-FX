package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.ProductInGeneralDish;

import java.sql.SQLException;
import java.util.List;

public class ProductsInGeneralDishManager extends BaseManager<ProductInGeneralDish> {
    public ProductsInGeneralDishManager() throws SQLException {
        super();
    }

    @Override
    public List<ProductInGeneralDish> getAll() throws SQLException {
        return null;
    }

    @Override
    public ProductInGeneralDish getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(ProductInGeneralDish entity) throws SQLException {

    }

    @Override
    public void update(ProductInGeneralDish entity) throws SQLException {

    }

    @Override
    public void delete(ProductInGeneralDish entity) throws SQLException {

    }
}
