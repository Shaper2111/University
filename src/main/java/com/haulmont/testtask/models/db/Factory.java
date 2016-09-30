package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.Group.GroupDao;
import com.haulmont.testtask.models.Group.IGroupDao;
import com.haulmont.testtask.models.Student.IStudentDao;
import com.haulmont.testtask.models.Student.StudentDao;

public class Factory implements IFactory {

    private static final IStudentDao studentDao = new StudentDao();

    private static final IGroupDao groupDao = new GroupDao();

    public static IStudentDao getStudentDao() { return studentDao; }

    public static IGroupDao getGroupDao() {
        return groupDao;
    }
}
