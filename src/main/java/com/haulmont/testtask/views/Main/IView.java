package com.haulmont.testtask.views.Main;

import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

/**
 * Basic view interface.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public interface IView<T extends IViewListener> extends View {
    void addListener(T listener);
    Component getDesign();
    void createNotify(String message);
    void createNotify(Exception e);
}
