package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.db.exceptions.DBException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * A singleton class, that controls connection to database.
 */
public class DBConnection {

    private static Properties props;

    private volatile static DBConnection instance;

    private Connection connection;


    private DBConnection() throws DBException {
        try {
            if (props == null) {
                props = new Properties();
                props.load(getConfigFile());
            }
            Class.forName(props.getProperty("DB_DRIVER"));
        } catch (ClassNotFoundException e) {
            throw new DBException("Failed to load JDBC Driver: ", e);
        } catch (IOException e) {
            props = null;
            throw new DBException("Failed to load DB config: ", e);
        }
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

    public Connection getConnection() {
        return connection;
    }

    private InputStream getConfigFile() throws IOException {
        try (InputStream file = getClass()
                .getResourceAsStream("db.properties")) {
            return file;
        }
    }

    private void connect() throws DBException {
        try {
            connection = DriverManager
                    .getConnection(props.getProperty("DB_URL"),
                            props.getProperty("DB_USER"),
                            props.getProperty("DB_PASS"));
        } catch (SQLException e) {
            instance = null;
            throw new DBException("Failed to connect with DB", e);
        }
    }

    void closeConnection() throws DBException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            instance = null;
        } catch (SQLException e) {
            throw new DBException("Error when closing connection", e);
        }
    }
}
