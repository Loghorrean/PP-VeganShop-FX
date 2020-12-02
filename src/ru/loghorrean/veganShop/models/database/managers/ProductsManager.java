package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsManager extends BaseManager<Product> {
    public ProductsManager() throws SQLException {
        super();
    }

    @Override
    public List<Product> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM products";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (set.next()) {
                System.out.println(CategoriesData.getInstance().getCategoryById(set.getInt("category_id")));
                Product product = new Product.ProductBuilder()
                        .withId(set.getInt("product_id"))
                        .withName(set.getString("product_name"))
                        .withDescription(set.getString("product_description"))
                        .withAmount(set.getFloat("product_amount"))
                        .withPrice(set.getInt("product_price"))
                        .withCalories(set.getInt("number_of_calories"))
                        .withAllergic(set.getBoolean("is_allergic"))
                        .withCategory(CategoriesData.getInstance().getCategoryById(set.getInt("category_id")))
                        .build();
                products.add(product);
            }
            return products;
        }
    }

    @Override
    public Product getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new Product.ProductBuilder()
                        .withId(set.getInt("product_id"))
                        .withName(set.getString("product_name"))
                        .withDescription(set.getString("product_description"))
                        .withAmount(set.getFloat("product_amount"))
                        .withPrice(set.getInt("product_price"))
                        .withCalories(set.getInt("number_of_calories"))
                        .withAllergic(set.getBoolean("is_allergic"))
                        .withCategory(CategoriesData.getInstance().getCategoryById(set.getInt("category_id")))
                        .build();
            }
            return null;
        }
    }

    @Override
    public void insert(Product product) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO PRODUCTS (product_name, product_description, product_amount, product_price, " +
                    "number_of_calories, is_allergic, category_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, product.getName());
            s.setString(2, product.getDescription());
            s.setFloat(3, product.getAmount());
            s.setInt(4, product.getPrice());
            s.setInt(5, product.getCalories());
            s.setBoolean(6, product.isAllergic());
            s.setInt(7, product.getCategory().getId());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                product.setId(set.getInt(1));
            }
            throw new SQLException("ERROR WHILE INSERTING PRODUCT");
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE products SET product_name = ?, product_description = ?, product_amount = ? " +
                    "product_price = ?, number_of_calories = ?, is_allergic = ?, category_id = ? WHERE product_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, product.getName());
            s.setString(2, product.getDescription());
            s.setFloat(3, product.getAmount());
            s.setInt(4, product.getPrice());
            s.setInt(5, product.getCalories());
            s.setBoolean(6, product.isAllergic());
            s.setInt(7, product.getCategory().getId());
            s.setInt(8, product.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING PRODUCT");
        }
    }

    @Override
    public void delete(Product product) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, product.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING PRODUCT");
        }
    }
}
