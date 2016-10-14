package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.db.exceptions.DBException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * A singleton class, that controls connection to database.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class DBConnection {

    private static Properties props;

    private volatile static DBConnection instance;

    private Connection connection;


    private DBConnection() throws DBException {
        try {
            if (props == null) {
                readConfigFile();
            }
            Class.forName(props.getProperty("DB_DRIVER"));
        } catch (ClassNotFoundException e) {
            throw new DBException("Ошибка загрузки JDBC драйвера.");
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

    private void readConfigFile() throws DBException {
        try (InputStream file = getClass().getClassLoader()
                .getResourceAsStream("db.properties")) {
            props = new Properties();
            props.load(file);
        } catch (IOException e) {
            throw new DBException("Ошибка чтения файла конфигурации.");
        }
    }

    private void connect() throws DBException {
        try {
            connection = DriverManager
                    .getConnection(props.getProperty("DB_URL"),
                            props.getProperty("DB_USER"),
                            props.getProperty("DB_PASS"));
            checkDB();
        } catch (SQLException e) {
            instance = null;
            throw new DBException("Ошибка соединения с базой данных");
        }
    }

    void closeConnection() throws DBException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            instance = null;
        } catch (SQLException e) {
            throw new DBException("Ошибка закрытия соединения с " +
                    "базой данных");
        }
    }

    private void checkDB() throws DBException {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(*) " +
                    "FROM INFORMATION_SCHEMA.TABLES " +
                    "WHERE TABLE_SCHEMA ='PUBLIC'");
            while (rs.next())
                if (rs.getInt(1) == 0){
                    initializeDB(props.getProperty("DB_CREATE_SCRIPT"));
                    initializeDB(props.getProperty("DB_INSERT_SCRIPT"));
                }

        } catch (SQLException e) {
            throw new DBException("Ошибка проверки БД.");
        }
    }

    private void initializeDB(String filename) throws DBException {
        URL file = DBConnection.class.getClassLoader()
                .getResource(filename);
        if (file == null)
            throw new DBException("Файл инициализации БД не найден.");
        try(BufferedReader reader = new BufferedReader(new
                FileReader(file.getFile()))){
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    builder.append(line);
                    if (!line.startsWith("commit;"))
                        continue;
                    try (Statement statement = getInstance()
                            .getConnection().createStatement()){
                        statement.execute(builder.toString());
                        builder = new StringBuilder();
                    }
                }
        } catch (SQLException e) {
            throw new DBException("Ошибка создания БД.");
        } catch (IOException e) {
            throw new DBException("Ошибка чтения файла инициализации.");
        }
    }
}
