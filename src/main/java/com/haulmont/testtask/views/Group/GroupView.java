package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.presenters.Group.GroupPresenter;
import com.haulmont.testtask.presenters.Group.IGroupViewListener;
import com.haulmont.testtask.views.Group.windows.AddGroupWindow;
import com.haulmont.testtask.views.Group.windows.DeleteGroupWindow;
import com.haulmont.testtask.views.Group.windows.EditGroupWindow;
import com.haulmont.testtask.views.Main.AbstractView;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;

import java.util.HashMap;

public class GroupView extends AbstractView<IGroupViewListener>
        implements IGroupView<IGroupViewListener> {

    public static final String VIEW_NAME = "groups";

    private final GroupViewDesign design = new GroupViewDesign();

    @Override
    public GroupViewDesign getDesign() {
        return design;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addListener(new GroupPresenter(this));
        addComponent(design);
        design.addNewButton.addClickListener(this::addNewClick);
        design.editButton.addClickListener(this::editClick);
        design.deleteButton.addClickListener(this::deleteClick);
        listeners.forEach(IGroupViewListener::showData);
    }


    public void generateGrid(BeanItemContainer container,
                             HashMap<String, String> columns){
        columns.forEach((id, name) -> {
            Grid.Column col = design.grid.addColumn(id);
            col.setHeaderCaption(name);
        });
        design.grid.setContainerDataSource(container);
    }

    @Override
    public void addElementToGrid(BeanItem item) {
        design.grid.getContainerDataSource().addItem(item.getBean());
    }

    @Override
    public void removeElementFromGrid(BeanItem item) {
        design.grid.getContainerDataSource().removeItem(item
                .getBean());
    }

    private void addNewClick(Button.ClickEvent event){
        new AddGroupWindow(res -> {
            for (IGroupViewListener listener : listeners)
                listener.createData(res);
        });
    }

    private void editClick(Button.ClickEvent event){
        Item item = design.grid.getContainerDataSource().getItem
                (design.grid
                .getSelectedRow());

        if (item == null) {
            createNotify("Не выбрана группа для редактирования.");
            return;
        }

        new EditGroupWindow(item, res -> {
            for (IGroupViewListener listener : listeners)
                listener.updateData(res);
        });
    }

    private void deleteClick(Button.ClickEvent event){
        Object row = design.grid.getSelectedRow();

        if (row == null) {
            createNotify("Не выбрана группа для удаления.");
            return;
        }
        new DeleteGroupWindow(row, res -> {
            for (IGroupViewListener listener : listeners)
                listener.deleteData(res);
        });
    }
}
