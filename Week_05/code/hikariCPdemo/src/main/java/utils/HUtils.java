package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HUtils {
    static HikariConfig config = new HikariConfig("/db.properties");
    static HikariDataSource ds = new HikariDataSource(config);
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void main(String[] args) {
        System.out.println(config.getJdbcUrl());
        System.out.println(config.getConnectionTimeout());
        System.out.println(config.getMaximumPoolSize());
        System.out.println(config.getMinimumIdle());
        System.out.println(config.getMaxLifetime());
    }

}
