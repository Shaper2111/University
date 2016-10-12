package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.views.Group.presenters.GroupPresenter;
import com.haulmont.testtask.views.Group.presenters.IGroupViewListener;
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

    private Grid grid;

    @Override
    public GroupViewDesign getDesign() {
        return design;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addListener(new GroupPresenter(this));
        addComponent(design);
        design.addNewButton.addClickListener((Button.ClickListener) (event3) -> addNewClick());
        design.editButton.addClickListener((Button.ClickListener) (event2) -> editClick());
        design.deleteButton.addClickListener((Button.ClickListener) (event1) -> deleteClick());
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
        design.groupContent.addComponent(grid);
    }

    @Override
    public void addElementToGrid(BeanItem item) {
        grid.getContainerDataSource().addItem(item.getBean());
    }

    @Override
    public void removeElementFromGrid(BeanItem item) {
        grid.getContainerDataSource().removeItem(item.getBean());
    }


    private void addNewClick(){
        new AddGroupWindow(res -> {
            for (IGroupViewListener listener : listeners)
                listener.createData(res);
        });
    }

    private void editClick(){
        Item item = grid.getContainerDataSource().getItem(grid
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

    private void deleteClick(){
        Object row = grid.getSelectedRow();

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
