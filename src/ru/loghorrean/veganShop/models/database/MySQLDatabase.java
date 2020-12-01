package ru.loghorrean.veganShop.models.database;

import com.mysql.cj.jdbc.MysqlDataSource;
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
            source.setServerName(MainConstants.HOST);
            source.setPort(MainConstants.PORT);
            source.setDatabaseName(MainConstants.DB_NAME);
            source.setUser(MainConstants.DB_USER);
            source.setPassword(MainConstants.DB_PASS);

            source.setCharacterEncoding("UTF-8");
            source.setServerTimezone("UTC");
        }
        return source.getConnection();
    }

}
