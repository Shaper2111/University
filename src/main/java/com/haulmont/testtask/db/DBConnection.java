/*
 * %W% %E% Firstname Lastname
 *
 * Copyright (c) 2016.
 *
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.haulmont.testtask.db;

import com.haulmont.testtask.db.exceptions.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A singleton class, that controls connection to database.
 */
class DBConnection {

    private Connection connection;

    private String name = "dbpath";

    private String user = "SA";

    private String passwd = "";

    private String path = "jdbc:hsqldb:file:" + name + "/";

    private String driver = "org.hsqldb.jdbcDriver";

    private volatile static DBConnection instance;


    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws DBException {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                    instance.connect();
                }
            }
        }
        return instance;
    }

    private DBConnection() throws DBException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DBException("Failed to load JDBC Driver: ", e);
        }
    }

    private void connect() throws DBException {
        try {
            connection = DriverManager.getConnection(path, user,
                    passwd);
        } catch (SQLException e) {
            instance = null;
            throw new DBException("Failed to connect with DB", e);
        }
    }

    public void closeConnection() throws DBException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            instance = null;
        } catch (SQLException e) {
            throw new DBException("Error when closing connection", e);
        }
    }
}
