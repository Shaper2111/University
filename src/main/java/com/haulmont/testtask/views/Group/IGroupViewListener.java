package com.haulmont.testtask.views.Group;

import com.haulmont.testtask.models.Group.Group;
import com.vaadin.data.util.BeanItem;

public interface IGroupViewListener {
    void showData();
    void processData(BeanItem<Group> item);
    void processData(Group obj);
}
