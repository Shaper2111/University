package com.haulmont.testtask.views.Student;

import com.haulmont.testtask.presenters.IViewListener;
import com.haulmont.testtask.views.Main.IView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.renderers.Renderer;

import java.util.HashMap;

/**
 * View interface that describes Student-specific methods.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 * @param <T> Student presenter interface
 */
interface IStudentView<T extends IViewListener> extends IView<T> {
    void filterData(BeanItemContainer container);
    void generateGrid(BeanItemContainer container, HashMap<String,
            String> columns, HashMap<String, Renderer<?>> renderers);
    void addElementToGrid(BeanItem item);
    void removeElementFromGrid(BeanItem item);
}
