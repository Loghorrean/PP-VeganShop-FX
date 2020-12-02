package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.MainData;
import ru.loghorrean.veganShop.models.ProfileData;
import ru.loghorrean.veganShop.models.database.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersManager extends BaseManager<User> {
    public UsersManager() throws SQLException {
        super();
    }

    @Override
    public List<User> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM users";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (set.next()) {
                users.add(new User.UserBuilder()
                        .withId(set.getInt("user_id"))
                        .withUsername(set.getString("username"))
                        .withEmail(set.getString("email"))
                        .withPassword(set.getString("password"))
                        .withFirstName(set.getString("firstname"))
                        .withLastName(set.getString("lastname"))
                        .withPhone(set.getString("user_phone"))
                        .withCity(ProfileData.getInstance().getCityById(set.getInt("user_city")))
                        .withStreet(set.getString("user_street"))
                        .withHouse(set.getInt("user_house"))
                        .withFlat(set.getInt("user_flat"))
                        .withSalt(set.getString("user_salt"))
                        .withRole(MainData.getInstance().computeRoleById(set.getInt("role_id")))
                        .build());
            }
            return users;
        }
    }

    @Override
    public User getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new User.UserBuilder()
                        .withId(set.getInt("user_id"))
                        .withUsername(set.getString("username"))
                        .withEmail(set.getString("email"))
                        .withPassword(set.getString("password"))
                        .withFirstName(set.getString("firstname"))
                        .withLastName(set.getString("lastname"))
                        .withPhone(set.getString("user_phone"))
                        .withCity(ProfileData.getInstance().getCityById(set.getInt("user_city")))
                        .withStreet(set.getString("user_street"))
                        .withHouse(set.getInt("user_house"))
                        .withFlat(set.getInt("user_flat"))
                        .withSalt(set.getString("user_salt"))
                        .withRole(MainData.getInstance().computeRoleById(set.getInt("role_id")))
                        .build();
            }
            return null;
        }
    }

    @Override
    public void insert(User user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO users (username, email, password, firstname, lastname, user_phone, " +
                    "user_city, user_street, user_house, user_flat, user_salt, role_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, user.getUsername());
            s.setString(2, user.getEmail());
            s.setString(3, user.getPassword());
            s.setString(4, user.getFirstName());
            s.setString(5, user.getLastName());
            s.setString(6, user.getPhone());
            if (user.getCity() != null) {
                s.setInt(7, user.getCity().getId());
            } else {
                s.setNull(7, Types.NULL);
            }
            s.setString(8, user.getStreet());
            s.setInt(9, user.getHouse());
            s.setInt(10, user.getFlat());
            s.setString(11, user.getSalt());
            s.setInt(12, user.getRole().getId());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                user.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERTING USER " + user.getUsername());
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "UPDATE users SET username = ?, email = ?, password = ?, firstname = ?, ";
            sql += "lastname = ?, user_phone = ?, user_city = ?, user_street = ?, user_house = ?, user_flat = ?, ";
            sql += "user_salt = ?, role_id = ? WHERE user_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, user.getUsername());
            s.setString(2, user.getEmail());
            s.setString(3, user.getPassword());
            s.setString(4, user.getFirstName());
            s.setString(5, user.getLastName());
            s.setString(6, user.getPhone());
            if (user.getCity() != null) {
                s.setInt(7, user.getCity().getId());
            } else {
                s.setNull(7, Types.NULL);
            }
            s.setString(8, user.getStreet());
            s.setInt(9, user.getHouse());
            s.setInt(10, user.getFlat());
            s.setString(11, user.getSalt());
            s.setInt(12, user.getRole().getId());
            s.setInt(13, user.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING USER " + user.getUsername());
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, user.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING USER " + user.getUsername());
        }
    }

    public boolean checkIfUserExists(String username) throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "SELECT username FROM users WHERE username = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, username);
            ResultSet result = s.executeQuery();
            return result.next();
        }
    }

    public boolean checkIfEmailExists(String email) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT username FROM users WHERE email = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, email);
            ResultSet result = s.executeQuery();
            return result.next();
        }
    }

    public User getUserById(int id) throws SQLException {
        try(Connection c = database.getConnection()) {
            MainData data = MainData.getInstance();
            ProfileData profileData = ProfileData.getInstance();
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new User.UserBuilder()
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

    public User getUserByUsername(String username) throws SQLException {
        try(Connection c = database.getConnection()) {
            MainData data = MainData.getInstance();
            ProfileData profileData = ProfileData.getInstance();
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, username);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new User.UserBuilder()
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
