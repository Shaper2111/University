package com.haulmont.testtask.views.Student;

import com.vaadin.navigator.View;

interface IStudentView extends View {
    void addListener(IStudentViewListener listener);
}
