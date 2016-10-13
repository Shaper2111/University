package com.haulmont.testtask.views.Main;

import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface IView<T extends IViewListener> extends View {
    void addListener(T listener);
    Component getDesign();
    void createNotify(String message);
}
