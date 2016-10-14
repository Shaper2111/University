package com.haulmont.testtask.models.Student;

import com.haulmont.testtask.models.db.DBConnection;
import com.haulmont.testtask.models.db.Factory;
import com.haulmont.testtask.models.db.GenericDao;
import com.haulmont.testtask.models.db.exceptions.DBException;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.sql.Date;
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
public class StudentDao extends GenericDao<Student, Long>
        implements IStudentDao<Student> {


    private List<Student> getListQuery(String sql) throws
            DaoException {
        try (PreparedStatement pres = DBConnection.getInstance()
                .getConnection().prepareStatement(sql)) {
            ResultSet rs = pres.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                list.add(parseResult(rs));
            }
            return list;
        } catch (DBException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException("При получении записей произошла" +
                    " ошибка.");
        }
    }

    @Override
    public Class<Long> getPkClass() {
        return Long.class;
    }

    @Override
    public List<Student> getAll() throws DaoException {
        return getListQuery("SELECT * FROM STUDENTS");
    }

    @Override
    public List getGroupsForSelect() throws DaoException {
        return Factory.getGroupDao().getNumbers();
    }

    @Override
    public List<Student> getStudentsBy(String lastName,
                                       Object groupNumber)
            throws DaoException {
        String sql = "SELECT * FROM STUDENTS";

        boolean isLastName = !lastName.isEmpty();
        if (isLastName) sql += " WHERE LASTNAME = '" + lastName + "'";
        if (groupNumber != null) {
            sql += isLastName ? " AND " : " WHERE ";
            sql += "GROUPNUMBER = " + groupNumber;
        }
        return getListQuery(sql);
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
