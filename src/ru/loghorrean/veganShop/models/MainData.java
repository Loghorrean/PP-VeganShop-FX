package ru.loghorrean.veganShop.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.loghorrean.veganShop.models.database.entities.RoleEntity;
import ru.loghorrean.veganShop.models.database.managers.RoleManager;
import ru.loghorrean.veganShop.models.database.managers.UserManager;

import java.sql.SQLException;
import java.util.List;

public class MainData {
    private static MainData instance;
    private static RoleManager roleManager;
    private static UserManager userManager;
    private static List<RoleEntity> roles;

    private MainData() throws SQLException {
        roleManager = RoleManager.getInstance();
        userManager = UserManager.getInstance();
        setRoles();
    }

    public static MainData getInstance() throws SQLException {
        if (instance == null) {
            instance = new MainData();
        }
        return instance;
    }

    public void setRoles() throws SQLException {
        roles = roleManager.getAllRoles();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public RoleManager getRoleManager() {
        return roleManager;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public RoleEntity getRoleByTitle(String title) {
        for(RoleEntity role: roles) {
            if (role.getTitle().equals(title)) {
                return role;
            }
        }
        return null;
    }

    public RoleEntity computeRoleById(int id) {
        for(RoleEntity role: roles) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }
}
