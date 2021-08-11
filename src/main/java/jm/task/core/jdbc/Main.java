package jm.task.core.jdbc;


import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException,
            SQLException{
        Util util = new Util();
        User user = new User();
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("Max", "Slashchev", (byte) 23);
//        userDaoJDBC.saveUser("Ivan", "Slashchev", (byte) 43);
//        userDaoJDBC.saveUser("Oleg", "Nikiforov", (byte) 29);
//        userDaoJDBC.saveUser("Aleksey", "Romanov", (byte) 20);
        //userDaoJDBC.getAllUsers();
        //userDaoJDBC.cleanUsersTable();
        //userDaoJDBC.dropUsersTable();
        //util.getConnection().close();
    }
}
