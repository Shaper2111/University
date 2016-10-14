package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.models.Group.GroupDao;
import com.haulmont.testtask.models.Group.IGroupDao;
import com.haulmont.testtask.models.Student.IStudentDao;
import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.models.Student.StudentDao;

/**
 * Factory class that gives DAO interfaces.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class Factory {

    private static final IStudentDao<Student> studentDao = new
            StudentDao();

    private static final IGroupDao<Group> groupDao = new
            GroupDao();

    public static IStudentDao<Student> getStudentDao() {
        return studentDao;
    }

    public static IGroupDao<Group> getGroupDao() {
        return groupDao;
    }
}
