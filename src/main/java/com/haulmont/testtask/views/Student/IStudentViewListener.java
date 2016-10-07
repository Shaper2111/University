package com.haulmont.testtask.views.Student;

import com.haulmont.testtask.models.Student.Student;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;

public interface IStudentViewListener {
    void showData();
    IndexedContainer getGroupsForSelect();
    void processData(BeanItem<Student> item);
    void processData(Student obj);
}
