package com.haulmont.testtask.models.Group;

import com.haulmont.testtask.models.db.DBConnection;
import com.haulmont.testtask.models.db.GenericDao;
import com.haulmont.testtask.models.db.exceptions.DBException;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class that realizes custom entity-specific methods.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class GroupDao extends GenericDao<Group, Long>
        implements IGroupDao<Group> {

    @Override
    public Class<Long> getPkClass() {
        return Long.class;
    }

    @Override
    public List<Group> getAll() throws DaoException {
        String sql = "SELECT * FROM GROUPS";
        try (PreparedStatement pres = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)) {
            List<Group> list = new ArrayList<>();
            ResultSet rs = pres.executeQuery();
            while (rs.next())
                list.add(parseResult(rs));
            return list;
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("При получении записей произошла" +
                    " ошибка.");
        }
    }

    @Override
    public List<Integer> getNumbers() throws DaoException {
        String sql = "SELECT NUMBER FROM GROUPS";
        try (PreparedStatement pres = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)) {
            List<Integer> list = new ArrayList<>();
            ResultSet rs = pres.executeQuery();
            while (rs.next())
                list.add(rs.getInt("number"));
            return list;
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("При получении записей произошла" +
                    " ошибка.");
        }
    }

    @Override
    public String getQuerySQL() {
        return "SELECT * FROM GROUPS WHERE ID = ?";
    }

    @Override
    public String createQuerySQL() {
        return "INSERT INTO GROUPS (NUMBER, DEPARTMENT) VALUES (?,?)";
    }

    @Override
    public String updateQuerySQL() {
        return "UPDATE GROUPS SET NUMBER = ?, DEPARTMENT = ? " +
                "WHERE ID = ?";
    }

    @Override
    public String deleteQuerySQL() {
        return "DELETE FROM GROUPS WHERE ID = ?";
    }

    @Override
    public void prepareCreateSQL(PreparedStatement pres, Group group)
            throws SQLException {
        pres.setInt(1, group.getNumber());
        pres.setString(2, group.getDepartment());
    }

    @Override
    public void prepareUpdateSQL(PreparedStatement pres, Group group)
            throws SQLException {
        pres.setInt(1, group.getNumber());
        pres.setString(2, group.getDepartment());
        pres.setLong(3, group.getId());
    }

    @Override
    public Group parseResult(ResultSet rs) throws SQLException {
        return new Group(rs.getLong("Id"), rs.getInt("Number"),
                rs.getString("Department"));
    }

}
