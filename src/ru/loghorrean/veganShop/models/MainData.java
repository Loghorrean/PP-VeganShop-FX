package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.Role;
import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.models.database.managers.RolesManager;
import ru.loghorrean.veganShop.models.database.managers.UsersManager;

import java.sql.SQLException;
import java.util.List;

public class MainData {
    private static MainData instance;
    private final RolesManager roleManager;
    private final UsersManager userManager;
    private List<Role> roles;

    private MainData() throws SQLException {
        roleManager = new RolesManager();
        userManager = new UsersManager();
        setRoles();
    }

    public static MainData getInstance() throws SQLException {
        if (instance == null) {
            instance = new MainData();
        }
        return instance;
    }

    public void setRoles() throws SQLException {
        roles = roleManager.getAll();
    }

    public UsersManager getUserManager() {
        return userManager;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Role getRoleByTitle(String title) {
        for(Role role: roles) {
            if (role.getTitle().equals(title)) {
                return role;
            }
        }
        return null;
    }

    public Role computeRoleById(int id) {
        for(Role role: roles) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }

    public void registerUser(User user) throws SQLException {
        userManager.insert(user);
    }
}
