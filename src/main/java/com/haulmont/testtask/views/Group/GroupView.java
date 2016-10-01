package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.views.Group.windows.AddGroupWindow;
import com.haulmont.testtask.views.Group.windows.DeleteGroupWindow;
import com.haulmont.testtask.views.Group.windows.EditGroupWindow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class GroupView extends GroupViewDesign implements IGroupView {

    public static final String VIEW_NAME = "groups";

    private List<IGroupViewListener> listeners = new ArrayList<>();

    private Grid grid;


    @Override
    public void addListener(IGroupViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addListener(new GroupPresenter(this));
        addNewButton.addClickListener(this::addNewClick);
        editButton.addClickListener(this::editClick);
        deleteButton.addClickListener(this::deleteClick);
        listeners.forEach(IGroupViewListener::showData);
    }

    void generateGrid(BeanItemContainer container){
        grid = new Grid(container);
        grid.setColumnOrder("id", "number", "department");
        grid.setSizeFull();
        this.addComponent(grid);
    }

    private void addNewClick(Button.ClickEvent event){
        AddGroupWindow addNewWindow = new AddGroupWindow
                ("Добавление группы");
        UI.getCurrent().addWindow(addNewWindow);
    }

    private void editClick(Button.ClickEvent event){
        Item item = grid.getContainerDataSource().getItem(grid
                .getSelectedRow());
        EditGroupWindow editWindow = new EditGroupWindow
                ("Редактирование группы", item);
        UI.getCurrent().addWindow(editWindow);
    }

    private void deleteClick(Button.ClickEvent event){

        DeleteGroupWindow deleteWindow = new DeleteGroupWindow
                ("Удаление группы", grid.getSelectedRow());
        UI.getCurrent().addWindow(deleteWindow);
    }
}
