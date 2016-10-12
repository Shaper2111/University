package com.haulmont.testtask.views.Main.forms;

import com.haulmont.testtask.models.Entity;
import com.vaadin.data.util.BeanItem;

interface IForm<T extends Entity> {
    BeanItem<T> createItem();
    BeanItem<T> getItem();
    BeanItem<T> commit();
}
