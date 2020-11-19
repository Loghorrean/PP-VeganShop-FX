package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.RoleEntity;
import ru.loghorrean.veganShop.util.Roles;

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
                        set.getInt("role_id"),
                        set.getString("role_name")
                ));
            }
            return roles;
        }
    }

    public Roles computeRole(int role_id) {
        switch (role_id) {
            case 1:
                return Roles.Admin;
            case 2:
                return Roles.Customer;
            case 3:
                return Roles.Courier;
        }
        return null;
    }
}
