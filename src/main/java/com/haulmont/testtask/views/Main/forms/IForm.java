package com.haulmont.testtask.views.Main.forms;

import com.haulmont.testtask.models.Entity;
import com.vaadin.data.util.BeanItem;

/**
 * Simple interface for form processing.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
interface IForm<T extends Entity> {
    BeanItem<T> createItem();
    BeanItem<T> getItem();
    BeanItem<T> commit();
}
