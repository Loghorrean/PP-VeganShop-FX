package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.util.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private MySQLDatabase database;

    private static UserManager instance;

    private UserManager(MySQLDatabase database) {
        this.database = database;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager(MySQLDatabase.getInstance());
        }
        return instance;
    }

    private static void insertMainInfo(UserEntity user) {
        String sql = "INSERT INTO users () VALUES ()";
    }

    private static void insertOptionalInfo(UserEntity user) {
        String sql = "";
    }

    public void registerUser(UserEntity user) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "INSERT INTO users (username, email, password, user_salt, role_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user.getUsername());
            s.setString(2, user.getEmail());
            s.setString(3, user.getPassword());
            s.setString(4, user.getSalt());
            s.setInt(5, user.getRole().getRoleCode());
            s.executeUpdate();
        }
    }

    public boolean checkIfUserExists(String username) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "SELECT username FROM users WHERE username = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, username);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                return true;
            }
            return false;
        }
    }

    public boolean checkIfEmailExists(String email) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT username FROM users WHERE email = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, email);
            ResultSet result = s.executeQuery();
            if (result.next()) {
                return true;
            }
            return false;
        }
    }

    public UserEntity getUserById(int id) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new UserEntity.UserBuilder()
                        .withId(set.getInt("user_id"))
                        .withUsername(set.getString("username"))
                        .withEmail(set.getString("email"))
                        .withPassword(set.getString("password"))
                        .build();
            }
            return null;
        }
    }
}
