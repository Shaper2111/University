package com.haulmont.testtask.views.Main;

import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractView<T extends IViewListener>
        extends VerticalLayout implements IView<T> {

    protected final List<T> listeners = new ArrayList<>();

    @Override
    public void addListener(T listener) {
        listeners.add(listener);
    }

    @Override
    public void createNotify(String message) {
        Notification.show(message);
    }
}
