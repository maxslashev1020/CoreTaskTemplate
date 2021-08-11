package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {
    Util util = new Util();
    Statement statement;

    {
        try {
            statement = util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement preparedStatement = null;
    private static final String INSERT_NEW = "INSERT INTO `mydbtest`.`User`(name, lastname, age) VALUES(?, ?, ?);";
    private static final String CREATE_TABLE = "CREATE TABLE `mydbtest`.`User` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `lastname` VARCHAR(45) NOT NULL,\n" +
            "  `age` INT NOT NULL,\n" +
            "  PRIMARY KEY (`id`));";
    private static final String DROP_TABLE = "DROP TABLE `mydbtest`.`User`;";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM `mydbtest`.`User` WHERE(`id` = ?)";
    private static final String GET_ALL_USERS = "SELECT * FROM `mydbtest`.`User`;";
    private static final String DELETE_ALL_USERS = "DELETE FROM `mydbtest`.`User`;";
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
        try {
            statement.execute(DELETE_ALL_USERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
