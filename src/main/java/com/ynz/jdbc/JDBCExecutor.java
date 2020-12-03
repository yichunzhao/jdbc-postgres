package com.ynz.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    public static void main(String[] args) {
        System.out.println("using jdbc...");
        String host = "localhost";
        String databaseName = "favoritecolors";
        String userName = "postgres";
        String password = "test";
        DatabaseConnectionManager dbManager = new DatabaseConnectionManager(host, databaseName, userName, password);

        try {
            Connection con = dbManager.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from colors");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
