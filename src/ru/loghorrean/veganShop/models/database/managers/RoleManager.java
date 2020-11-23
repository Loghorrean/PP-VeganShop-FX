package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.RoleEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleManager {
    private MySQLDatabase database;
    private static RoleManager instance;

    private RoleManager(MySQLDatabase database) {
        this.database = database;
    }

    public static RoleManager getInstance() {
        if (instance == null) {
            instance = new RoleManager(MySQLDatabase.getInstance());
        }
        return instance;
    }

    public List<RoleEntity> getAllRoles() throws SQLException {
        try(Connection c = database.getConnection()) {
            Statement s = c.createStatement();
            String sql = "SELECT * FROM roles";
            ResultSet set = s.executeQuery(sql);
            List<RoleEntity> roles = new ArrayList<>();
            while (set.next()) {
                roles.add(new RoleEntity(
                        set.getInt("id"),
                        set.getString("title")
                ));
            }
            return roles;
        }
    }
}
