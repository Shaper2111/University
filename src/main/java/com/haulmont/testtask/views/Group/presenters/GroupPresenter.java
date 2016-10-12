package com.haulmont.testtask.views.Group.presenters;

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
            e.printStackTrace();
        }
        HashMap<String, String> columns = new LinkedHashMap<>();

        columns.put("id", "Идентификатор");
        columns.put("number", "Номер группы");
        columns.put("department", "Факультет");

        view.generateGrid(container, columns);
    }

    @Override
    public void createData(BeanItem<Group> item) {
        Group group = item.getBean();
        try {
            group.setId(dao.create(group));
            view.addElementToGrid(item);
            view.createNotify("Группа успешно добавлена.");
        } catch (DaoException e) {
            view.createNotify("Ошибка создания: " + e.getMessage());
        }
    }

    @Override
    public void updateData(BeanItem<Group> item) {
        Group group = item.getBean();
        try {
            dao.update(group);
            view.createNotify("Группа успешно обновлена.");
        } catch (DaoException e) {
            view.createNotify("Ошибка обновления: " + e.getMessage());
        }
    }

    @Override
    public void deleteData(BeanItem<Group> item) {
        try {
            dao.delete(item.getBean().getId());
            view.removeElementFromGrid(item);
            view.createNotify("Группа успешно удалена.");
        } catch (DaoException e) {
            view.createNotify("Ошибка удаления: " + e.getMessage());
        }
    }
}
