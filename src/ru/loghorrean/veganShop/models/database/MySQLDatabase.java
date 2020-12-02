package ru.loghorrean.veganShop.models.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import ru.loghorrean.veganShop.util.DbConstants;
import ru.loghorrean.veganShop.util.MainConstants;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDatabase {
    private MysqlDataSource source;

    private static MySQLDatabase instance;

    private MySQLDatabase() {

    }

    public static MySQLDatabase getInstance() {
        if (instance == null) {
            instance = new MySQLDatabase();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (source == null) {
            source = new MysqlDataSource();
            source.setServerName(DbConstants.HOST);
            source.setPort(DbConstants.PORT);
            source.setDatabaseName(DbConstants.DB_NAME);
            source.setUser(DbConstants.DB_USER);
            source.setPassword(DbConstants.DB_PASS);

            source.setCharacterEncoding("UTF-8");
            source.setServerTimezone("UTC");
        }
        return source.getConnection();
    }

}
