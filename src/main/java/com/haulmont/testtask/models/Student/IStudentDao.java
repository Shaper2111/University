package com.haulmont.testtask.models.Student;

import com.haulmont.testtask.models.Entity;
import com.haulmont.testtask.models.db.IGenericDao;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.util.List;

/**
 * DAO interface for all Student POJO CRUD operations.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 * @param <Student> Student POJO
 */
public interface IStudentDao<Student extends Entity> extends
        IGenericDao<Student, Long> {
    
    List<Student> getAll() throws DaoException;

    List getGroupsForSelect() throws DaoException;

    List<Student> getStudentsBy(String lastName, Object groupNumber)
            throws DaoException;
}
