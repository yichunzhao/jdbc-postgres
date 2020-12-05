package com.ynz.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {

    public static Connection getLocalPostgresConnection(String databaseName, String userName, String password) {
        String hostname = "localhost";
        String subProtocol = "postgresql";

        String url = createJDBCDatabaseUrl(subProtocol, hostname, databaseName);
        Connection conn = null;
        Properties props = new Properties();
        props.put("user", userName);
        props.put("password", password);

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return conn;
    }

    private static String createJDBCDatabaseUrl(String subProtocol, String hostname, String databaseName) {
        return "jdbc:" + subProtocol + "://" + hostname + "/" + databaseName;
    }

}
