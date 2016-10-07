package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.models.Group.IGroupDao;
import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.models.db.Factory;
import com.haulmont.testtask.models.db.exceptions.DaoException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;


class GroupPresenter implements IGroupViewListener {
    private IGroupDao dao;

    private IGroupView view;

    GroupPresenter(GroupView view){
        this.view = view;
        this.dao = Factory.getGroupDao();
    }

    public void showData(){
        BeanItemContainer<Group> container =
                new BeanItemContainer<>(Group.class);
        try {
            for (Object o: dao.getAll()) container.addBean((Group) o);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        view.generateGrid(container);
    }

    @Override
    public void processData(BeanItem<Group> item) {
        Group group = item.getBean();
        if (group.getId() == null) {
            createData(group);
        } else {
            updateData(group);
        }
    }

    @Override
    public void processData(Group obj) {
        try {
            dao.delete(obj);
        } catch (DaoException e) {
            view.createNotify("Ошибка удаления: " + e.getMessage());
            return;
        }
        BeanItem<Group> item = new BeanItem<>(obj);
        view.removeElementFromGrid(item);
        view.createNotify("Группа успешно удалена.");
    }

    private void createData(Group group){
        try {
            group.setId((Long) dao.create(group));
        } catch (DaoException e) {
            view.createNotify("Ошибка: " + e.getMessage());
            return;
        }
        BeanItem<Group> item = new BeanItem<>(group);
        view.addElementToGrid(item);
        view.createNotify("Группа успешно добавлена.");
    }

    private void updateData(Group group){
        try {
            dao.update(group);
        } catch (DaoException e) {
            view.createNotify("Ошибка обновления: " + e.getMessage());
            return;
        }
        view.createNotify("Группа успешно обновлена.");
    }
}
