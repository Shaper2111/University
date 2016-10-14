package com.haulmont.testtask.views.Main;

import com.haulmont.testtask.views.Group.GroupView;
import com.haulmont.testtask.views.Student.StudentView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * Main view for navigation between other views.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class NavigatorView extends NavigatorViewDesign implements
        ViewDisplay {

    private static final String STYLE_SELECTED = "menu-selected";

    private final Navigator navigator;

    public NavigatorView() {
        navigator = new Navigator(UI.getCurrent(),
                (ViewDisplay) this);
        addViewToNavigator(GroupView.VIEW_NAME, GroupView.class,
                menuButton1);
        addViewToNavigator(StudentView.VIEW_NAME, StudentView.class,
                menuButton2);
        if (navigator.getState().isEmpty()) {
            navigator.navigateTo(GroupView.VIEW_NAME);
        }
    }

    private void doNavigate(String viewName){
        getUI().getNavigator().navigateTo(viewName);
    }

    private void addViewToNavigator(String viewName,
                            Class<? extends View> viewClass,
                            Button menuButton){
        navigator.addView(viewName, viewClass);
        menuButton.addClickListener(event -> doNavigate(viewName));
        menuButton.setData(viewClass.getName());
    }

    private void adjustStyleByData(Component component, Object data) {
        if (component instanceof Button) {
            if (data != null && data.equals
                    (((Button) component).getData())) {
                component.addStyleName(STYLE_SELECTED);
            } else {
                component.removeStyleName(STYLE_SELECTED);
            }
        }
    }

    @Override
    public void showView(View view) {
        if (view instanceof Component) {
        scrollPanel.setContent((Component) view);
            for (Component aSideBar : sideBar) {
                adjustStyleByData(aSideBar, view.getClass().getName());
            }
        } else
            throw new IllegalArgumentException
                    ("View is not a Component");
    }
}
