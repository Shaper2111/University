package com.haulmont.testtask.models.Student;

import com.haulmont.testtask.models.db.IGenericDao;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;


public interface IStudentDao<PK extends Serializable> extends
        IGenericDao<Student, PK> {

    List<Student> getAll() throws DaoException;
}
