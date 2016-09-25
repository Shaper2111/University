package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.Entity;
import com.haulmont.testtask.models.db.exceptions.DBException;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class GenericDao<T extends Entity, PK extends Serializable>
        implements IGenericDao<T, PK> {

    private final transient Class<PK> pkClass;

    protected GenericDao(Class<PK> pkClass) {
        this.pkClass = pkClass;
    }

    @Override
    public PK create(T obj) throws DaoException {
        String sql = createQuerySQL();
        try (PreparedStatement pres = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)) {
            prepareCreateSQL(pres, obj);
            pres.executeQuery();
            try (ResultSet res = pres.getGeneratedKeys()) {
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
        try (PreparedStatement pres = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)) {
            pres.setObject(1, Id);
            ResultSet rs = pres.executeQuery();
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
        try (PreparedStatement pres = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)) {
            prepareUpdateSQL(pres, object);
            pres.executeQuery();
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
        try (PreparedStatement pres = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)) {
            pres.setObject(1, object.getId());
            pres.executeQuery();
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("Error while execute get SQL " +
                    "statement", e);
        }
    }
}
