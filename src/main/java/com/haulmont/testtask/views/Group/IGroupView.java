package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.presenters.IViewListener;
import com.haulmont.testtask.views.Main.IView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

import java.util.HashMap;

/**
 * Simple interface for vaadin-based view. Holds specific methods
 * for represent data to user.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public interface IGroupView<T extends IViewListener> extends IView<T>{
    void generateGrid(BeanItemContainer container, HashMap<String,
            String> columns);
    void addElementToGrid(BeanItem item);
    void removeElementFromGrid(BeanItem item);
}
