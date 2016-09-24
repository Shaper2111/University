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
import com.haulmont.testtask.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class StudentDao extends GenericDao<Student, Long>
        implements IStudentDao {

    StudentDao(Class<Long> longClass) {
        super(longClass);
    }

    @Override
    public List<Student> getAll() throws DaoException {
        return null;
    }

    @Override
    public String getQuerySQL() {
        return "SELECT * FROM STUDENTS";
    }

    @Override
    public String createQuerySQL(Student st) {
        return "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME, MIDNAME," +
                "BIRTHDATE, GROUPNUMBER) VALUES ('"
                + st.getFirstName() + "','"
                + st.getLastName() + "','"
                + st.getMidName() + "','"
                + st.getBirthDate() + "','"
                + st.getGroupNumber() + "');";
    }

    @Override
    public Student parseResult(ResultSet rs) throws SQLException {
        return new Student(rs.getLong("Id"),
                rs.getString("firstName"), rs.getString("lastName"),
                rs.getString("midName"), rs.getDate("birthDate"),
                rs.getInt("groupNumber"));
    }
}
