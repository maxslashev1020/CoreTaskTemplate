package jm.task.core.jdbc.dao;

import com.sun.jmx.remote.internal.ArrayQueue;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao  {
    Util util = new Util();
    Statement statement = util.getConnection().createStatement();
    PreparedStatement preparedStatement = null;
    public static final String INSERT_NEW = "INSERT INTO `mydbtest`.`User`(name, lastname, age) VALUES(?, ?, ?);";
    public static final String CREATE_TABLE = "CREATE TABLE `mydbtest`.`User` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `lastname` VARCHAR(45) NOT NULL,\n" +
            "  `age` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`));";
    public static final String DROP_TABLE = "DROP TABLE `mydbtest`.`User`;";
    public static final String REMOVE_USER_BY_ID = "DELETE FROM `mydbtest`.`User` WHERE(`id` = ?)";
    public static final String GET_ALL_USERS = "SELECT * FROM `mydbtest`.`User`;";
    public static final String DELETE_ALL_USERS = "DELETE FROM `mydbtest`.`User`;";
    public UserDaoJDBCImpl() throws ClassNotFoundException,
            SQLException {
//
    }

    public void createUsersTable() {
        try {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        try {
            statement.execute(DROP_TABLE);
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            preparedStatement = util.getConnection().prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            preparedStatement = util.getConnection().prepareStatement(REMOVE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        User user = new User();
        try {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            statement.execute(DELETE_ALL_USERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
