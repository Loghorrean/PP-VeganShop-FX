package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoriesManager {
    private MySQLDatabase database;
    private static ProductCategoriesManager manager;

    private ProductCategoriesManager(MySQLDatabase database) {
        this.database = database;
    }

    public static ProductCategoriesManager getInstance() {
        if (manager == null) {
            manager = new ProductCategoriesManager(MySQLDatabase.getInstance());
        }
        return manager;
    }

    public List<ProductCategory> getCategories() throws SQLException {
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

    public void insertCategory(ProductCategory category) throws SQLException {
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

            throw new SQLException("CATEGORY NOT INSERTED");
        }
    }

    public void updateCategory(ProductCategory category) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE product_categories SET category_name = ?, category_description = ? WHERE category_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, category.getName());
            s.setString(2, category.getDescription());
            s.setInt(3, category.getId());
            s.executeUpdate();
        }
    }

    public void deleteCategory(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM product_categories WHERE category_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
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

    public ProductCategory getCategoryById(int id) throws SQLException {
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
