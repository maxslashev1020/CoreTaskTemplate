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
    public UserDaoJDBCImpl() throws ClassNotFoundException,
            SQLException {
//
    }

    public void createUsersTable() {
        final String CREATE_TABLE = "CREATE TABLE `mydbtest`.`User` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        final String DROP_TABLE = "DROP TABLE `mydbtest`.`User`;";
        try {
            statement.execute(DROP_TABLE);
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String INSERT_NEW = "INSERT INTO `mydbtest`.`User`(name, lastname, age) VALUES(?, ?, ?);";
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
        final String REMOVE_USER_BY_ID = "DELETE FROM `mydbtest`.`User` WHERE(`id` = ?)";
        try {
            preparedStatement = util.getConnection().prepareStatement(REMOVE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        final String GET_ALL_USERS = "SELECT * FROM `mydbtest`.`User`;";
        List<User> list = new ArrayList<User>();
        try {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(id, name, lastName, age);
                list.add(user);
                System.out.println(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        final String DELETE_ALL_USERS = "DELETE FROM `mydbtest`.`User`;";
        try {
            statement.execute(DELETE_ALL_USERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
