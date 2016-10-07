package com.haulmont.testtask.views.Student;

import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.haulmont.testtask.views.Student.windows.AddStudentWindow;
import com.haulmont.testtask.views.Student.windows.DeleteStudentWindow;
import com.haulmont.testtask.views.Student.windows.EditStudentWindow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

import java.util.ArrayList;
import java.util.List;

public class StudentView extends StudentViewDesign
        implements IStudentView {

    public static final String VIEW_NAME = "students";

    private List<IStudentViewListener> listeners = new ArrayList<>();

    private Grid grid;

    private ModalWindow addNewWindow = new ModalWindow("Добавление " +
            "профиля студента");

    private ModalWindow editWindow = new ModalWindow
            ("Редактирование профиля студента");

    private ModalWindow deleteWindow = new ModalWindow("Удаление " +
            "профиля студента");


    public ModalWindow getAddNewWindow() {
        return addNewWindow;
    }

    public ModalWindow getEditWindow() { return editWindow; }

    public ModalWindow getDeleteWindow() {
        return deleteWindow;
    }

    @Override
    public void addListener(IStudentViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public List<IStudentViewListener> getListeners() {
        return listeners;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addListener(new StudentPresenter(this));
        addNewButton.addClickListener(this::addNewClick);
        editButton.addClickListener(this::editClick);
        deleteButton.addClickListener(this::deleteClick);
        listeners.forEach(IStudentViewListener::showData);
    }

    @Override
    public void generateGrid(BeanItemContainer container){
        grid = new Grid(container);
        grid.setColumnOrder("id", "firstName", "midName", "lastName",
                "birthDate", "groupNumber");
        grid.setSizeFull();
        addComponent(grid);
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

    public IndexedContainer getGroupsForSelect(){
        for (IStudentViewListener listener: listeners)
            return listener.getGroupsForSelect();
        return null;
    }

    private void addNewClick(Button.ClickEvent event){
        new AddStudentWindow(this);
    }

    private void editClick(Button.ClickEvent event){
        Item item = grid.getContainerDataSource().getItem(grid
                .getSelectedRow());
        if (item == null) {
            Notification.show("Не выбран профиль для редактирования" +
                    ".");
            return;
        }

        new EditStudentWindow(item, this);
    }

    private void deleteClick(Button.ClickEvent event){
        Object row = grid.getSelectedRow();
        if (row == null) {
            Notification.show("Не выбран профиль для удаления.");
            return;
        }
        new DeleteStudentWindow(grid.getSelectedRow(), this);
    }

}
