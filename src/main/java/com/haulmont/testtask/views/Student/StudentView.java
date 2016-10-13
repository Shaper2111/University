package com.haulmont.testtask.views.Student;

import com.haulmont.testtask.presenters.Student.IStudentViewListener;
import com.haulmont.testtask.presenters.Student.StudentPresenter;
import com.haulmont.testtask.views.Main.AbstractView;
import com.haulmont.testtask.views.Student.forms.StudentFilterForm;
import com.haulmont.testtask.views.Student.windows.AddStudentWindow;
import com.haulmont.testtask.views.Student.windows.DeleteStudentWindow;
import com.haulmont.testtask.views.Student.windows.EditStudentWindow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.Renderer;

import java.util.HashMap;

public class StudentView extends AbstractView<IStudentViewListener>
        implements IStudentView<IStudentViewListener> {

    public static final String VIEW_NAME = "students";

    private IndexedContainer groups = new IndexedContainer();

    private final StudentViewDesign design = new StudentViewDesign();

    @Override
    public Component getDesign() {
        return design;
    }

    public void setGroups(IndexedContainer groups) {
        this.groups = groups;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addListener(new StudentPresenter(this));
        addComponent(design);
        design.addNewButton.addClickListener(this::addNewClick);
        design.editButton.addClickListener(this::editClick);
        design.deleteButton.addClickListener(this::deleteClick);

        listeners.forEach(IStudentViewListener::showData);
    }

    @Override
    public void generateGrid(BeanItemContainer container,
                             HashMap<String, String> columns,
                             HashMap<String, Renderer<?>> renderers){
        columns.forEach((id, name) -> {
            Grid.Column col = design.grid.addColumn(id);
            col.setHeaderCaption(name);
        });
        design.grid.setContainerDataSource(container);
        renderers.forEach((column, renderer) -> {
            Grid.Column col = design.grid.getColumn(column);
            col.setRenderer(renderer);
        });
        createFilter();
    }

    private void createFilter() {
        new StudentFilterForm(design.grid, (res1, res2) -> {
            for (IStudentViewListener listener : listeners)
                listener.filterData(res1, res2);
        });
    }

    @Override
    public void filterData(BeanItemContainer container) {
        design.grid.setContainerDataSource(container);
    }

    @Override
    public void addElementToGrid(BeanItem item) {
        design.grid.getContainerDataSource().addItem
                (item.getBean());
    }

    @Override
    public void removeElementFromGrid(BeanItem item) {
        design.grid.getContainerDataSource().removeItem
                (item.getBean());
    }

    private void addNewClick(Button.ClickEvent event){
        new AddStudentWindow(groups, res -> {
            for (IStudentViewListener listener : listeners)
                listener.createData(res);
        });
    }

    private void editClick(Button.ClickEvent event){
        Item item = design.grid
                .getContainerDataSource()
                .getItem(design.grid
                .getSelectedRow());
        if (item == null) {
            createNotify("Не выбран профиль для редактирования.");
            return;
        }

        new EditStudentWindow(item, groups,
            res -> {for (IStudentViewListener listener : listeners)
                    listener.updateData(res);
        });
    }

    private void deleteClick(Button.ClickEvent event){
        Object row = design.grid.getSelectedRow();
        if (row == null) {
            Notification.show("Не выбран профиль для удаления.");
            return;
        }
        new DeleteStudentWindow(row, res -> {
                for (IStudentViewListener listener : listeners)
                    listener.deleteData(res);
        });
    }
}
