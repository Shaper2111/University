package com.haulmont.testtask.views;

import com.haulmont.testtask.views.Main.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

@Theme("university")
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        setContent(new MainView());
    }
}