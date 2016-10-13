package com.haulmont.testtask.presenters.Group;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.data.util.BeanItem;

public interface IGroupViewListener extends IViewListener {
    void createData(BeanItem<Group> item);
    void updateData(BeanItem<Group> item);
    void deleteData(BeanItem<Group> item);
}
