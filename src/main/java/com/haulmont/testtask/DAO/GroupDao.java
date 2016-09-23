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
import com.haulmont.testtask.models.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class GroupDao extends GenericDao<Group, Long> implements IGroupDao {

    @Override
    public List<Group> getAll() throws DaoException {
        return null;
    }

    @Override
    public String getQuerySQL() {
        return "SELECT * FROM GROUPS";
    }

    @Override
    public String createQuerySQL(Group g) {
        return "INSERT INTO GROUPS (NUMBER, DEPARTMENT) VALUES ('"
                + g.getNumber() + "','"
                + g.getDepartment() + "');";
    }

    @Override
    public Group parseResult(ResultSet rs) throws SQLException {
        return new Group(rs.getLong("Id"), rs.getInt("Number"),
                rs.getString("Department"));
    }
}
