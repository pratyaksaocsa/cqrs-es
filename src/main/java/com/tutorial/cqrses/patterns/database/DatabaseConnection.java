package com.tutorial.cqrses.patterns.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static final String DB_URL = "jdbc:mysql://localhost:3306/YOUR_DATABASE?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String DB_DRV = "com.mysql.cj.jdbc.Driver";
    static final String DB_USER = "YOUR_USERNAME";
    static final String DB_PASSWD = "YOUR_PASSWORD";

    static Connection connection;

    public static Connection getDBConnection() {
        if(connection == null) {
            try {
                Class.forName(DB_DRV).getDeclaredConstructor()
                    .newInstance();
                connection = DriverManager.
                    getConnection(DB_URL, DB_USER, DB_PASSWD);
                //System.out.println("DB connected");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException | 
                    NoSuchMethodException | IllegalAccessException | 
                    InvocationTargetException | InstantiationException ex) {
                ex.printStackTrace();
            }
        }
        return connection;
    }

}