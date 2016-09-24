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
import com.haulmont.testtask.models.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class StudentDao extends GenericDao<Student, Long>
        implements IStudentDao<Long> {

    StudentDao() {
        super(Long.class);
    }

    @Override
    public List<Student> getAll() throws DaoException {
        String sql = "SELECT * FROM STUDENTS";
        try (PreparedStatement prest = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)){
            ResultSet rs = prest.executeQuery();
            List<Student> list = new ArrayList<>();
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
        return "SELECT * FROM STUDENTS WHERE ID = ?";
    }

    @Override
    public String createQuerySQL() {
        return "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME, MIDNAME," +
                "BIRTHDATE, GROUPNUMBER) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String updateQuerySQL() {
        return "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ?, " +
                "MIDNAME = ?, BIRTHDATE = ?, GROUPNUMBER = ?" +
                " WHERE ID = ?";
    }

    @Override
    public String deleteQuerySQL() {
        return "DELETE FROM STUDENTS WHERE ID = ?";
    }

    @Override
    public void prepareCreateSQL(PreparedStatement prest, Student st)
            throws SQLException {
        prest.setString(1, st.getFirstName());
        prest.setString(2, st.getLastName());
        prest.setString(3, st.getMidName());
        prest.setDate(4, new Date(st.getBirthDate().getTime()));
        prest.setInt(5, st.getGroupNumber());
    }

    @Override
    public void prepareUpdateSQL(PreparedStatement prest, Student st)
            throws SQLException {
        prest.setString(1, st.getFirstName());
        prest.setString(2, st.getLastName());
        prest.setString(3, st.getMidName());
        prest.setDate(4, new Date(st.getBirthDate().getTime()));
        prest.setInt(5, st.getGroupNumber());
        prest.setLong(6, st.getId());
    }

    @Override
    public Student parseResult(ResultSet rs) throws SQLException {
        return new Student(rs.getLong("Id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("midName"),
                rs.getDate("birthDate"),
                rs.getInt("groupNumber")
        );
    }
}
