package com.haulmont.testtask.presenters.Student;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.data.util.BeanItem;

public interface IStudentViewListener extends IViewListener {
    void filterData(String lastName, Object groupNumber);
    void createData(BeanItem<Student> item);
    void updateData(BeanItem<Student> item);
    void deleteData(BeanItem<Student> item);
}
