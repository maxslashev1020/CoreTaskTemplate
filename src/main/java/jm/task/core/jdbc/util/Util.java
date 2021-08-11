package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {

    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "rootroot";

    private  Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
