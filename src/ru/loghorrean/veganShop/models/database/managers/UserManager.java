package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.models.ProfileData;
import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.UserEntity;
import ru.loghorrean.veganShop.util.HashCompiler;

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

    public void registerUser(UserEntity user) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "INSERT INTO users (username, email, password, user_salt, role_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user.getUsername());
            s.setString(2, user.getEmail());
            s.setString(3, user.getPassword());
            s.setString(4, user.getSalt());
            s.setInt(5, user.getRole().getId());
            s.executeUpdate();
        }
    }

    public void updateUser(UserEntity user) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "UPDATE users SET username = ?, email = ?, password = ?, firstname = ?, ";
            sql += "lastname = ?, user_phone = ?, user_city = ?, user_street = ?, user_house = ?, user_flat = ?, ";
            sql += "user_salt = ?, role_id = ? WHERE user_id = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user.getUsername());
            s.setString(2, user.getEmail());
            s.setString(3, user.getPassword());
            s.setString(4, user.getFirstName());
            s.setString(5, user.getLastName());
            s.setString(6, user.getPhone());
            s.setInt(7, user.getCity().getId());
            s.setString(8, user.getStreet());
            s.setInt(9, user.getHouse());
            s.setInt(10, user.getFlat());
            s.setString(11, user.getSalt());
            s.setInt(12, user.getRole().getId());
            s.setInt(13, user.getId());
            s.executeUpdate();
        }
    }

    public UserEntity authoriseUser(String username) throws SQLException {
        return this.getUserByUsername(username);
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
            MainData data = MainData.getInstance();
            ProfileData profileData = ProfileData.getInstance();
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
                        .withFirstName(set.getString("firstname"))
                        .withLastName(set.getString("lastname"))
                        .withPhone(set.getString("user_phone"))
                        .withSalt(set.getString("user_salt"))
                        .withRole(data.computeRoleById(set.getInt("role_id")))
                        .withCity(profileData.getCityById(set.getInt("user_city")))
                        .withStreet(set.getString("user_street"))
                        .withHouse(set.getInt("user_house"))
                        .withFlat(set.getInt("user_flat"))
                        .build();
            }
            return null;
        }
    }

    public UserEntity getUserByUsername(String username) throws SQLException {
        try(Connection c = database.getConnection()) {
            MainData data = MainData.getInstance();
            ProfileData profileData = ProfileData.getInstance();
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, username);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new UserEntity.UserBuilder()
                        .withId(set.getInt("user_id"))
                        .withUsername(set.getString("username"))
                        .withEmail(set.getString("email"))
                        .withPassword(set.getString("password"))
                        .withFirstName(set.getString("firstname"))
                        .withLastName(set.getString("lastname"))
                        .withPhone(set.getString("user_phone"))
                        .withRole(data.computeRoleById(set.getInt("role_id")))
                        .withSalt(set.getString("user_salt"))
                        .withCity(profileData.getCityById(set.getInt("user_city")))
                        .withStreet(set.getString("user_street"))
                        .withHouse(set.getInt("user_house"))
                        .withFlat(set.getInt("user_flat"))
                        .build();
            }
            return null;
        }
    }
}
