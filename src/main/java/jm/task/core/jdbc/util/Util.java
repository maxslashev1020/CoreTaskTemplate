package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;


public class Util {

    private  Connection connection;

    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "rootroot";
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";


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

    public SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();

            settings.put(Environment.DRIVER, DB_DRIVER);
            settings.put(Environment.URL, HOST);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sessionFactory;
    }
}
