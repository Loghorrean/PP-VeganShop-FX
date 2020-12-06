package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.User;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.UsersManager;

import java.sql.SQLException;
import java.util.List;

public class UsersData {
    private static UsersData instance;
    private BaseManager<User> manager;
    private List<User> users;

    private UsersData() {
        try {
            manager = new UsersManager();
            setUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UsersData getInstance() {
        if (instance == null) {
            instance = new UsersData();
        }
        return instance;
    }

    public void setUsers() {
        try {
            users = manager.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) throws SQLException {
        manager.insert(user);
        users.add(user);
    }

    public void removeUser(User user) throws SQLException {
        manager.delete(user);
        users.remove(user);
    }

    public User getUserByName(String name) {
        for (User user: users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        for (User user: users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
