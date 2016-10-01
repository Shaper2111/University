package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.models.Group.IGroupDao;
import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.models.db.Factory;
import com.haulmont.testtask.models.db.exceptions.DaoException;
import com.vaadin.data.util.BeanItemContainer;


class GroupPresenter implements IGroupViewListener {
    private IGroupDao dao;

    private GroupView view;

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
}
