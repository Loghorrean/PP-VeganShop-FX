package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.GeneralDishesData;
import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.database.entities.ProductInGeneralDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsInGeneralDishManager extends BaseManager<ProductInGeneralDish> {
    public ProductsInGeneralDishManager() throws SQLException {
        super();
    }

    @Override
    public List<ProductInGeneralDish> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM products_in_general_dishes";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<ProductInGeneralDish> links = new ArrayList<>();
            while (set.next()) {
                links.add(new ProductInGeneralDish(
                        set.getInt("record_id"),
                        GeneralDishesData.getInstance().getDishById(set.getInt("dish_id")),
                        ProductsData.getInstance().getProductById(set.getInt("product_id")),
                        set.getInt("product_amount")
                ));
            }
            return links;
        }
    }

    @Override
    public ProductInGeneralDish getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM products_in_general_dishes WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new ProductInGeneralDish(
                        set.getInt("record_id"),
                        GeneralDishesData.getInstance().getDishById(set.getInt("dish_id")),
                        ProductsData.getInstance().getProductById(set.getInt("product_id")),
                        set.getInt("product_amount")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(ProductInGeneralDish link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO products_in_general_dishes (product_id, dish_id, product_amount) VALUES (?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, link.getProduct().getId());
            s.setInt(2, link.getDish().getId());
            s.setFloat(3, link.getAmount());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                link.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERTING LINK BETWEEN " + link.getDish().getName() + " AND " + link.getProduct().getName());
        }
    }

    @Override
    public void update(ProductInGeneralDish link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE products_in_general_dishes SET product_id = ?, dish_id = ?, " +
                    "product_amount = ? WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, link.getProduct().getId());
            s.setInt(2, link.getDish().getId());
            s.setFloat(3, link.getAmount());
            s.setInt(4, link.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING LINK BETWEEN " + link.getDish().getName() + " AND " + link.getProduct().getName());
        }
    }

    @Override
    public void delete(ProductInGeneralDish link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM products_general_dishes WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, link.getId());
            if (s.executeUpdate() == 1) {
                link.destroyLink();
                return;
            }
            throw new SQLException("ERROR WHILE DELETING LINK BETWEEN " + link.getDish().getName() + " AND " + link.getProduct().getName());
        }
    }
}
