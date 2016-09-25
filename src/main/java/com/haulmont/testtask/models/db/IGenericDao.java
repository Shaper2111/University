package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.Entity;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface IGenericDao<T extends Entity, PK extends Serializable> {

    PK create(T obj) throws DaoException;

    T get(PK Id) throws DaoException;

    void update(T object) throws DaoException;

    void delete(T object) throws DaoException;

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