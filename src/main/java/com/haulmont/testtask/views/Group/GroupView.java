package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.views.Group.windows.AddGroupWindow;
import com.haulmont.testtask.views.Group.windows.DeleteGroupWindow;
import com.haulmont.testtask.views.Group.windows.EditGroupWindow;
import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupView extends GroupViewDesign implements IGroupView {

    public static final String VIEW_NAME = "groups";

    private List<IGroupViewListener> listeners = new ArrayList<>();

    private Grid grid;

    private ModalWindow addNewWindow = new ModalWindow("Добавление группы");

    private ModalWindow editWindow = new ModalWindow("Редактирование группы");

    private ModalWindow deleteWindow = new ModalWindow("Удаление группы");


    public ModalWindow getAddNewWindow() {
        return addNewWindow;
    }

    public ModalWindow getEditWindow() {
        return editWindow;
    }

    public ModalWindow getDeleteWindow() {
        return deleteWindow;
    }

    @Override
    public void addListener(IGroupViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public List<IGroupViewListener> getListeners() {
        return listeners;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addListener(new GroupPresenter(this));
        addNewButton.addClickListener(this::addNewClick);
        editButton.addClickListener(this::editClick);
        deleteButton.addClickListener(this::deleteClick);
        listeners.forEach(IGroupViewListener::showData);
    }


    public void generateGrid(BeanItemContainer container,
                             HashMap<String, String> columns){
        grid = new Grid();
        columns.forEach((id, name) -> {
            Grid.Column col = grid.addColumn(id);
            col.setHeaderCaption(name);
        });
        grid.setContainerDataSource(container);
        grid.setSizeFull();
        this.addComponent(grid);
    }

    @Override
    public void addElementToGrid(BeanItem item) {
        grid.getContainerDataSource().addItem(item.getBean());
    }

    @Override
    public void removeElementFromGrid(BeanItem item) {
        grid.getContainerDataSource().removeItem(item.getBean());
    }


    @Override
    public void createNotify(String message) {
        Notification.show(message);
    }


    private void addNewClick(Button.ClickEvent event){
        new AddGroupWindow(this);
    }

    private void editClick(Button.ClickEvent event){
        Item item = grid.getContainerDataSource().getItem(grid
                .getSelectedRow());

        if (item == null) {
            Notification.show("Не выбрана группа для редактирования" +
                    ".");
            return;
        }

        new EditGroupWindow(item, this);
    }

    private void deleteClick(Button.ClickEvent event){
        Object row = grid.getSelectedRow();

        if (row == null) {
            Notification.show("Не выбрана группа для удаления.");
            return;
        }
        new DeleteGroupWindow(row, this);
    }

}
