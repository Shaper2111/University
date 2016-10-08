package com.haulmont.testtask.views.Student;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;

import java.util.HashMap;
import java.util.List;

interface IStudentView extends View {
    void addListener(IStudentViewListener listener);
    List<IStudentViewListener> getListeners();
    void generateGrid(BeanItemContainer container, HashMap<String,
            String> columns);
    void filterData(BeanItemContainer container);
    void addElementToGrid(BeanItem item);
    void removeElementFromGrid(BeanItem item);
    void createNotify(String message);
}
