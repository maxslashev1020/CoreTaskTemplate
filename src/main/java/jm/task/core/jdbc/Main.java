package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException,
            SQLException{
        Util util = new Util();
        User user = new User();
        UserServiceImpl userService = new UserServiceImpl();
        //userService.createUsersTable();
        //userService.saveUser("Max", "Slashchev", (byte) 23);
//        userService.saveUser("Ivan", "Slashchev", (byte) 43);
//        userService.saveUser("Oleg", "Nikiforov", (byte) 29);
//        userService.saveUser("Aleksey", "Romanov", (byte) 20);
        //userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}
