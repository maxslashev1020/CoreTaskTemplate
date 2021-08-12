package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {


    public static void main(String[] args) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Max", "Slashev", (byte) 23);
        userDao.saveUser("Ivan", "Slashchev", (byte) 43);
        userDao.saveUser("Oleg", "Nikiforov", (byte) 29);
        userDao.saveUser("Aleksey", "Romanov", (byte) 20);
        userDao.getAllUsers();
        userDao.removeUserById(2L);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.getAllUsers();
        userDao.dropUsersTable();
    }
}
