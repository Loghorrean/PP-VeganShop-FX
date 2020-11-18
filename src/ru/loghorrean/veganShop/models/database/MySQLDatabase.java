package ru.loghorrean.veganShop.models.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDatabase {
    private String host;
    private int port;
    private String dbname;
    private String user;
    private String password;

    private MysqlDataSource source;

    private static MySQLDatabase instance;

    private MySQLDatabase(String host, int port, String dbname, String user, String password) {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
    }

    private MySQLDatabase(String host, String dbname, String user, String password) {
        this(host, 3306, dbname, user, password);
    }

    public static MySQLDatabase getInstance() {
        if (instance == null) {
            instance = new MySQLDatabase("localhost", 3306, "vegan_for_pp", "user_for_pp", "1234");
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (source == null) {
            source = new MysqlDataSource();
            source.setServerName(this.host);
            source.setPort(this.port);
            source.setDatabaseName(this.dbname);
            source.setUser(this.user);
            source.setPassword(this.password);

            source.setCharacterEncoding("UTF-8");
            source.setServerTimezone("UTC");
        }
        return source.getConnection();
    }

}
