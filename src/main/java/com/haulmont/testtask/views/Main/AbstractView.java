package com.haulmont.testtask.views.Main;

import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic abstract view.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public abstract class AbstractView<T extends IViewListener>
        extends VerticalLayout implements IView<T> {

    protected final List<T> listeners = new ArrayList<>();

    @Override
    public void addListener(T listener) {
        listeners.add(listener);
    }

    @Override
    public void createNotify(String message) {
        Notification notif = new Notification
                (message, Notification.Type.HUMANIZED_MESSAGE);
        notif.setDelayMsec(3000);
        notif.setPosition(Position.BOTTOM_RIGHT);
        notif.show(Page.getCurrent());
    }

    @Override
    public void createNotify(Exception e) {
        Notification notif = new Notification
                (e.getMessage(), Notification.Type.WARNING_MESSAGE);
        notif.setDelayMsec(5000);
        notif.setPosition(Position.BOTTOM_RIGHT);
        notif.show(Page.getCurrent());
    }
}