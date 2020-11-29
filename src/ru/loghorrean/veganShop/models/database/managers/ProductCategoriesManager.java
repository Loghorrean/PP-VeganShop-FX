package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoriesManager extends BaseManager<ProductCategory> {
    private ProductCategoriesManager() {
       super();
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "SELECT * FROM product_categories";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            ArrayList<ProductCategory> categories = new ArrayList<>();
            while (set.next()) {
                categories.add(new ProductCategory(
                        set.getInt("category_id"),
                        set.getString("category_name"),
                        set.getString("category_description")
                ));
            }
            return categories;
        }
    }

    @Override
    public ProductCategory getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM product_categories WHERE category_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery(sql);
            if (set.next()) {
                return new ProductCategory(
                        set.getInt("category_id"),
                        set.getString("category_name"),
                        set.getString("category_description")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(ProductCategory category) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO product_categories (category_name, category_description) VALUES (?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, category.getName());
            s.setString(2, category.getDescription());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                category.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE ADDING PRODUCT CATEGORY");
        }
    }

    @Override
    public void update(ProductCategory category) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE product_categories SET category_name = ?, category_description = ? WHERE category_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, category.getName());
            s.setString(2, category.getDescription());
            s.setInt(3, category.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING CATEGORY " + category.getName());
        }
    }

    @Override
    public void delete(ProductCategory category) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM product_categories WHERE category_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, category.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING CATEGORY " + category.getName());
        }
    }

    public boolean checkIfCategoryExists(String catName) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM product_categories WHERE category_name = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, catName);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return true;
            }
            return false;
        }
    }

    public ProductCategory getCategoryByName(String name) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM product_categories WHERE category_name = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, name);
            ResultSet set = s.executeQuery(sql);
            if (set.next()) {
                return new ProductCategory(
                        set.getInt("category_id"),
                        set.getString("category_name"),
                        set.getString("category_description")
                );
            }
            return null;
        }
    }
}
