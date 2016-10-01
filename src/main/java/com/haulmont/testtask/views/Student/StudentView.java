package com.haulmont.testtask.views.Student;

import com.haulmont.testtask.views.Student.windows.AddStudentWindow;
import com.haulmont.testtask.views.Student.windows.DeleteStudentWindow;
import com.haulmont.testtask.views.Student.windows.EditStudentWindow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class StudentView extends StudentViewDesign
        implements IStudentView {

    public static final String VIEW_NAME = "students";

    private List<IStudentViewListener> listeners = new ArrayList<>();

    private Grid grid;


    @Override
    public void addListener(IStudentViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addListener(new StudentPresenter(this));
        addNewButton.addClickListener(this::addNewClick);
        editButton.addClickListener(this::editClick);
        deleteButton.addClickListener(this::deleteClick);
        listeners.forEach(IStudentViewListener::showData);
    }

    void generateGrid(BeanItemContainer container){
        grid = new Grid(container);
        grid.setColumnOrder("id", "firstName", "midName", "lastName",
                "birthDate", "groupNumber");
        grid.setSizeFull();
        addComponent(grid);
    }

    private void addNewClick(Button.ClickEvent event){
        AddStudentWindow addNewWindow = new AddStudentWindow
                ("Добавление профиля студента");
        UI.getCurrent().addWindow(addNewWindow);
    }

    private void editClick(Button.ClickEvent event){
        Item item = grid.getContainerDataSource().getItem(grid
                .getSelectedRow());
        EditStudentWindow editWindow = new EditStudentWindow
                ("Редактирование профиля студента", item);
        UI.getCurrent().addWindow(editWindow);
    }

    private void deleteClick(Button.ClickEvent event){

        DeleteStudentWindow deleteWindow = new DeleteStudentWindow
                ("Удаление профиля студента", grid.getSelectedRow());
        UI.getCurrent().addWindow(deleteWindow);
    }
}
