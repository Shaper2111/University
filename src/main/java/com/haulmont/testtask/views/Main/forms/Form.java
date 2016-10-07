package com.haulmont.testtask.views.Main.forms;

import com.haulmont.testtask.models.Entity;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;

public abstract class Form<T extends Entity> extends CustomComponent
        implements IForm<T>{

    protected FormLayout form = new FormLayout();

    protected FieldGroup binder;

    public Form(){
        binder = new FieldGroup(createItem());

        generateForm();

        setCompositionRoot(form);
    }


    public Form(Item item){
        binder = new FieldGroup(item);

        generateForm();

        setCompositionRoot(form);
    }

    private void generateForm(){
        form.setMargin(false);
        form.setSizeUndefined();
    }

    @Override
    public BeanItem<T> getItem(){
        return (BeanItem<T>) binder.getItemDataSource();
    }
}
