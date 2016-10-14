package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.Entity;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Generic interface that describes basic CRUD methods.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public interface IGenericDao<T extends Entity, PK extends
        Serializable> {

    /**
     * Method that gives primary key class for casting.
     *
     * @return as example Long.class
     */
    Class<PK> getPkClass();

    PK create(T obj) throws DaoException;

    T get(PK Id) throws DaoException;

    boolean update(T object) throws DaoException;

    void delete(PK Id) throws DaoException;

    String getQuerySQL();

    String createQuerySQL();

    String updateQuerySQL();

    String deleteQuerySQL();

    void prepareCreateSQL(PreparedStatement pres, T obj)
            throws SQLException;

    void prepareUpdateSQL(PreparedStatement pres, T obj)
            throws SQLException;

    T parseResult(ResultSet rs) throws SQLException;

}
