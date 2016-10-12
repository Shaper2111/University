package com.haulmont.testtask.views.Student.presenters;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.views.Main.IViewListener;
import com.vaadin.data.util.BeanItem;

public interface IStudentViewListener extends IViewListener {
    void filterData(String lastName, Object groupNumber);
    void createData(BeanItem<Student> item);
    void updateData(BeanItem<Student> item);
    void deleteData(BeanItem<Student> item);
}
