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
import com.haulmont.testtask.db.DBConnection;
import com.haulmont.testtask.db.exceptions.DBException;
import com.haulmont.testtask.models.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class GroupDao extends GenericDao<Group, Long>
        implements IGroupDao<Long> {

    GroupDao() {
        super(Long.class);
    }

    @Override
    public List<Group> getAll() throws DaoException {
        String sql = "SELECT * FROM GROUPS";
        try (PreparedStatement prest = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)){
            List<Group> list = new ArrayList<>();
            ResultSet rs = prest.executeQuery();
            while (rs.next()){
                list.add(parseResult(rs));
            }
            return list;
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("Error while execute bulk get " +
                    "SQL statement", e);
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
    public void prepareCreateSQL(PreparedStatement prest, Group group)
            throws SQLException {
        prest.setInt(1, group.getNumber());
        prest.setString(2, group.getDepartment());
    }

    @Override
    public void prepareUpdateSQL(PreparedStatement prest, Group group)
            throws SQLException {
        prest.setInt(1, group.getNumber());
        prest.setString(2, group.getDepartment());
        prest.setLong(3, group.getId());
    }

    @Override
    public Group parseResult(ResultSet rs) throws SQLException {
        return new Group(rs.getLong("Id"), rs.getInt("Number"),
                rs.getString("Department"));
    }
}
