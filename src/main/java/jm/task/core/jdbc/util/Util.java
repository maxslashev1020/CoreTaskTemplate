package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {

    private  Connection connection;

    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "rootroot";
    
    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Не удалось подключиться к базе");
            }
        }
        return connection;
    }
}
