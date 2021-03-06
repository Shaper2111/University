package com.haulmont.testtask.presenters.Group;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.models.Group.IGroupDao;
import com.haulmont.testtask.models.db.Factory;
import com.haulmont.testtask.models.db.exceptions.DaoException;
import com.haulmont.testtask.views.Group.GroupView;
import com.haulmont.testtask.views.Group.IGroupView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Presenter realization for Group entity. Process data, returns it
 * to view and catch exceptions.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class GroupPresenter implements IGroupViewListener {
    private final IGroupDao<Group> dao;

    private final IGroupView view;

    public GroupPresenter(GroupView view){
        this.view = view;
        this.dao = Factory.getGroupDao();
    }

    @Override
    public void showData(){
        BeanItemContainer<Group> container =
                new BeanItemContainer<>(Group.class);
        try {
            container.addAll(dao.getAll());
        } catch (DaoException e) {
            view.createNotify(e);
        }

        view.generateGrid(container, setColumns());
    }

    private HashMap<String, String> setColumns(){
        HashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("id", "Идентификатор");
        columns.put("number", "Номер группы");
        columns.put("department", "Факультет");
        return columns;
    }

    @Override
    public void createData(BeanItem<Group> item) {
        Group group = item.getBean();
        try {
            group.setId(dao.create(group));
            view.addElementToGrid(item);
            view.createNotify("Группа успешно добавлена.");
        } catch (DaoException e) {
            view.createNotify(e);
        }
    }

    @Override
    public void updateData(BeanItem<Group> item) {
        Group group = item.getBean();
        try {
            dao.update(group);
            view.createNotify("Группа успешно обновлена.");
        } catch (DaoException e) {
            view.createNotify(e);
        }
    }

    @Override
    public void deleteData(BeanItem<Group> item) {
        try {
            dao.delete(item.getBean().getId());
            view.removeElementFromGrid(item);
            view.createNotify("Группа успешно удалена.");
        } catch (DaoException e) {
            view.createNotify(e);
        }
    }
}
