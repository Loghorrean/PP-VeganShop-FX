package ru.loghorrean.veganShop.models.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDatabase {
    private final String host = "localhost";
    private final int port = 3306;
    private final String dbname = "vegan_for_pp";
    private final String user = "user_for_pp";
    private final String password = "1234";

    private MysqlDataSource source;
    private Connection connection;

    private static MySQLDatabase instance;

    private MySQLDatabase() throws SQLException {
        source = setDataSource();
        connection = source.getConnection();
    }

    public static MySQLDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySQLDatabase();
        }
        return instance;
    }

    private MysqlDataSource setDataSource() throws SQLException {
        source = new MysqlDataSource();
        source.setServerName(host);
        source.setPort(port);
        source.setDatabaseName(dbname);
        source.setUser(user);
        source.setPassword(password);

        source.setCharacterEncoding("UTF-8");
        source.setServerTimezone("UTC");
        return source;
    }

    public Connection getConnection() {
        return connection;
    }

}
