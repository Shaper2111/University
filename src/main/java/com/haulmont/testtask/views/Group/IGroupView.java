package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.views.Main.IView;
import com.haulmont.testtask.views.Main.IViewListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

import java.util.HashMap;

public interface IGroupView<T extends IViewListener> extends
        IView<T> {
    void generateGrid(BeanItemContainer container, HashMap<String,
            String> columns);
    void addElementToGrid(BeanItem item);
    void removeElementFromGrid(BeanItem item);
}
