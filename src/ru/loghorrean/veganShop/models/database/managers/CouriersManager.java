package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.Courier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouriersManager extends BaseManager<Courier> {
    public CouriersManager() throws SQLException {
        super();
    }

    @Override
    public List<Courier> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM couriers";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<Courier> couriers = new ArrayList<>();
            while (set.next()) {
                couriers.add(new Courier(
                        set.getInt("courier_id"),
                        set.getString("courier_firstname"),
                        set.getString("courier_lastname"),
                        set.getString("courier_phone"),
                        set.getString("verify_code")
                ));
            }
            return couriers;
        }
    }

    @Override
    public Courier getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM couriers WHERE courier_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new Courier(
                        set.getInt("courier_id"),
                        set.getString("courier_firstname"),
                        set.getString("courier_lastname"),
                        set.getString("courier_phone"),
                        set.getString("verify_code")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(Courier courier) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO couriers (firstname, lastname, " +
                    "phone, verify_code) VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, courier.getFirstname());
            s.setString(2, courier.getLastname());
            s.setString(3, courier.getPhone());
            s.setString(4, courier.getVerifyCode());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                courier.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE ADDING COURIER");
        }
    }

    @Override
    public void update(Courier courier) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE couriers SET firstname = ?, lastname = ?, phone = ?, " +
                    "verify_code = ? WHERE courier_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, courier.getFirstname());
            s.setString(2, courier.getLastname());
            s.setString(3, courier.getPhone());
            s.setString(4, courier.getVerifyCode());
            s.setInt(5, courier.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING COURIER");
        }
    }

    @Override
    public void delete(Courier courier) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM couriers WHERE courier_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, courier.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING COURIER");
        }
    }
}
