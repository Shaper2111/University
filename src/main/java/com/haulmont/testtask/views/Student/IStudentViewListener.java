package com.haulmont.testtask.views.Student;

import com.vaadin.data.util.BeanItemContainer;

public interface IStudentViewListener {
    void showData();
    BeanItemContainer getGroupsForSelect();
}
