package com.haulmont.testtask.models.db;

import com.haulmont.testtask.models.Group.GroupDao;
import com.haulmont.testtask.models.Group.IGroupDao;
import com.haulmont.testtask.models.Student.IStudentDao;
import com.haulmont.testtask.models.Student.StudentDao;

public class Factory implements IFactory {

    @Override
    public IStudentDao getStudentDao() {
        return new StudentDao();
    }

    @Override
    public IGroupDao getGroupDao() {
        return new GroupDao();
    }
}
