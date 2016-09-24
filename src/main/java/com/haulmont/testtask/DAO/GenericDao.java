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

    private final transient Class<PK> pkClass;

    GenericDao(Class<PK> pkClass){
        this.pkClass = pkClass;
    }

    @Override
    public PK create(T obj) throws DaoException {
        String sql = createQuerySQL();
        try (PreparedStatement prest = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)){
            prepareCreateSQL(prest, obj);
            ResultSet rs = prest.executeQuery();
            try (ResultSet res = prest.getGeneratedKeys()){
                res.next();
                return pkClass.cast(res.getObject(1));
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
        String sql = getQuerySQL();
        try (PreparedStatement prest = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)){
            prest.setObject(1, Id);
            ResultSet rs = prest.executeQuery();
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
        String sql = updateQuerySQL();
        try (PreparedStatement prest = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)){
            prepareUpdateSQL(prest, object);
            ResultSet rs = prest.executeQuery();
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("Error while execute create SQL " +
                    "statement", e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        String sql = deleteQuerySQL();
        try (PreparedStatement prest = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)){
            prest.setObject(1, object.getId());
            ResultSet rs = prest.executeQuery();
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("Error while execute get SQL " +
                    "statement", e);
        }
    }
}
