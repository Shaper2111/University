package com.haulmont.testtask.views.Main.forms;

import com.haulmont.testtask.models.Entity;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;

/**
 * Abstract Form class, that holds form properties and returns
 * result after form validation. Suppress unchecked warnings
 * because there is no way to check Item-to-BeanItem<T> casting.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
@SuppressWarnings("unchecked")
public abstract class Form<T extends Entity> extends CustomComponent
        implements IForm<T>{

    protected final FormLayout form = new FormLayout();

    protected final FieldGroup binder = new FieldGroup();

    protected Form(){
        binder.setItemDataSource(createItem());

        generateForm();

        setCompositionRoot(form);
    }


    protected Form(Item item){
        binder.setItemDataSource(item);

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
