package com.haulmont.testtask.views.Group;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;

import java.util.HashMap;
import java.util.List;

interface IGroupView extends View {
    void addListener(IGroupViewListener listener);
    List<IGroupViewListener> getListeners();
    void generateGrid(BeanItemContainer container, HashMap<String,
            String> columns);
    void addElementToGrid(BeanItem item);
    void removeElementFromGrid(BeanItem item);
    void createNotify(String message);
}
