package com.haulmont.testtask.views.Main;

import com.haulmont.testtask.views.Group.GroupView;
import com.haulmont.testtask.views.Student.StudentView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class MainView extends MainViewDesign  implements ViewDisplay {

    private Navigator navigator;

    public MainView() {
        navigator = new Navigator(UI.getCurrent(),
                (ViewDisplay) this);
        addViewtoNavigator(GroupView.VIEW_NAME, GroupView.class,
                menuButton1);
        addViewtoNavigator(StudentView.VIEW_NAME, StudentView.class,
                menuButton2);
        if (navigator.getState().isEmpty()) {
            navigator.navigateTo(GroupView.VIEW_NAME);
        }
    }

    private void doNavigate(String viewName){
        getUI().getNavigator().navigateTo(viewName);
    }

    void addViewtoNavigator(String viewName,
                            Class<? extends View> viewClass,
                            Button menuButton){
        navigator.addView(viewName, viewClass);
        menuButton.addClickListener(event -> doNavigate(viewName));
        menuButton.setData(viewClass.getName());
    }

    @Override
    public void showView(View view) {
        if (view instanceof Component) {
            scroll_panel.setContent((Component) view);
        } else {
            throw new IllegalArgumentException("View is not a " +
                    "Component");
        }
    }
}