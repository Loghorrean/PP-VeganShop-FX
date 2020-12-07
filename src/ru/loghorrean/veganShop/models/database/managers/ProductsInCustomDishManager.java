package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.CustomDishesData;
import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.database.entities.ProductInCustomDish;

import java.sql.*;
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
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new ProductInCustomDish(
                        set.getInt("record_id"),
                        ProductsData.getInstance().getProductById(set.getInt("product_id")),
                        CustomDishesData.getInstance().getDishById(set.getInt("dish_id")),
                        set.getInt("product_amount"),
                        set.getString("product_recipe")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(ProductInCustomDish link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO products_in_custom_dishes (product_id, dish_id, product_amount, product_recipe) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, link.getProduct().getId());
            s.setInt(2, link.getDish().getId());
            s.setInt(3, link.getAmount());
            s.setString(4, link.getRecipe());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                link.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERTING LINK");
        }
    }

    @Override
    public void update(ProductInCustomDish link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE products_in_custom_dishes SET product_id = ?, dish_id = ?. product_amount = ?, " +
                    "product_recipe = ? WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, link.getProduct().getId());
            s.setInt(2, link.getDish().getId());
            s.setInt(3, link.getAmount());
            s.setString(4, link.getRecipe());
            s.setInt(5, link.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING LINK");
        }
    }

    @Override
    public void delete(ProductInCustomDish link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM products_in_custom_dishes WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, link.getId());
            if (s.executeUpdate() == 1) {
                link.destroyLink();
                return;
            }
            throw new SQLException("ERROR WHILE DELETING LINK");
        }
    }
}
