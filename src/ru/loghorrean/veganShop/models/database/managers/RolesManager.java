package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolesManager extends BaseManager<Role> {
    public RolesManager() throws SQLException {
        super();
    }

    @Override
    public List<Role> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM roles";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<Role> roles = new ArrayList<>();
            while (set.next()) {
                roles.add(new Role(
                        set.getInt("id"),
                        set.getString("title")
                ));
            }
            return roles;
        }
    }

    @Override
    public Role getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM roles WHERE id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new Role(
                        set.getInt("id"),
                        set.getString("title")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(Role role) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "INSERT INTO roles (title) VALUES (?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, role.getTitle());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                role.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE ADDING ROLE");
        }
    }

    @Override
    public void update(Role role) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "UPDATE roles SET title = ? WHERE id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, role.getTitle());
            s.setInt(2, role.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING ROLE");
        }
    }

    @Override
    public void delete(Role role) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "DELETE FROM roles WHERE id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, role.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING ROLE");
        }
    }
}
