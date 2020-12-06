package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.CustomDishesData;
import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.database.entities.ProductInCustomDish;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsInCustomDishManager extends BaseManager<ProductInCustomDish> {
    public ProductsInCustomDishManager() throws SQLException {
        super();
    }

    @Override
    public List<ProductInCustomDish> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM products_in_custom_dishes";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<ProductInCustomDish> links = new ArrayList<>();
            while(set.next()) {
                links.add(new ProductInCustomDish(
                        set.getInt("record_id"),
                        ProductsData.getInstance().getProductById(set.getInt("product_id")),
                        CustomDishesData.getInstance().getDishById(set.getInt("dish_id")),
                        set.getInt("product_amount"),
                        set.getString("product_recipe")
                ));
            }
            return links;
        }
    }

    @Override
    public ProductInCustomDish getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM products_in_custom_dishes WHERE record_id = ?";
            return null;
        }
    }

    @Override
    public void insert(ProductInCustomDish entity) throws SQLException {
        try (Connection c = database.getConnection()) {

        }
    }

    @Override
    public void update(ProductInCustomDish entity) throws SQLException {
        try (Connection c = database.getConnection()) {

        }
    }

    @Override
    public void delete(ProductInCustomDish entity) throws SQLException {
        try (Connection c = database.getConnection()) {

        }
    }
}
