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

package com.haulmont.testtask.DAO;

import com.haulmont.testtask.DAO.exceptions.DaoException;
import com.haulmont.testtask.db.exceptions.DBException;
import com.haulmont.testtask.models.Entity;
import com.haulmont.testtask.db.DBConnection;

import java.io.Serializable;
import java.sql.*;


abstract class GenericDao<T extends Entity, PK extends Serializable>
        implements IGenericDao<T, PK> {

    @Override
    public PK create(T obj) throws DaoException {
        String sql = createQuerySQL(obj);
        try (Statement st = DBConnection.getInstance()
                .getConnection().createStatement()){
            ResultSet rs = st.executeQuery(sql);
            try (ResultSet res = st.getGeneratedKeys()){
                res.next();
                return (PK) res.getObject(1);
            }
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("Error while execute create SQL " +
                    "statement", e);
        }
    }

    @Override
    public T get(PK Id) throws DaoException {
        String sql = getQuerySQL() + " WHERE ID = " + Id + ";";
        try (Statement st = DBConnection.getInstance()
                .getConnection().createStatement()){
            ResultSet rs = st.executeQuery(sql);
            return parseResult(rs);
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("Error while execute get SQL " +
                    "statement", e);
        }
    }

    @Override
    public void update(T object) throws DaoException {

    }

    @Override
    public void delete(T object) throws DaoException {

    }
}
