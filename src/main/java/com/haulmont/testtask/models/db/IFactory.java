package com.haulmont.testtask.models.db;


import com.haulmont.testtask.models.Group.IGroupDao;
import com.haulmont.testtask.models.Student.IStudentDao;

interface IFactory {

    static IStudentDao getStudentDao(){ return null; }

    static IGroupDao getGroupDao() { return null; }
}
