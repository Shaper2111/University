package com.haulmont.testtask.presenters.Group;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.data.util.BeanItem;

/**
 * Presenter interface. Presents methods for data processing.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public interface IGroupViewListener extends IViewListener {
    void createData(BeanItem<Group> item);
    void updateData(BeanItem<Group> item);
    void deleteData(BeanItem<Group> item);
}
