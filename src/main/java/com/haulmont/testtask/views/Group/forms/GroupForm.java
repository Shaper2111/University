package com.haulmont.testtask.views.Group.forms;

import com.haulmont.testtask.models.Group.Group;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class GroupForm extends CustomComponent {

    private FormLayout form;

    private FieldGroup binder;

    private TextField id = new TextField("Идентификатор:");

    private TextField number = new TextField("Номер группы:");

    private TextField department = new TextField("Факультет:");

    public GroupForm(){
        BeanItem<Group> item = new BeanItem<>(new Group());

        binder = new FieldGroup(item);
        binder.bindMemberFields(this);

        generateForm();

        setCompositionRoot(form);
    }

    public GroupForm(Item item){

        binder = new FieldGroup(item);
        binder.bindMemberFields(this);

        generateForm();

        setCompositionRoot(form);
    }

    private void generateForm(){
        form = new FormLayout();
        form.setMargin(false);
        form.setSizeUndefined();

        validateId();
        validateNumber();
        validateDepartment();
    }

    private void validateId(){
        id.setNullRepresentation("");
        id.setReadOnly(true);
        form.addComponent(id);
    }

    private void validateNumber(){
        number.setRequired(true);
        number.addValidator(new NullValidator("Это поле обязательно."
                , false));
        number.addValidator(new IntegerRangeValidator("Введите 4 " +
                "цифры."
                , 1, 9999));
        number.setImmediate(true);
        number.setNullRepresentation("");
        form.addComponent(number);
    }

    private void validateDepartment(){
        department.setRequired(true);
        department.addValidator(new StringLengthValidator("Введите " +
                "до 40 символов.", 1, 40, false));
        department.setImmediate(true);
        department.setNullRepresentation("");
        form.addComponent(department);
    }

    public Item commit(){
        try {
            binder.commit();
            return binder.getItemDataSource();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Ошибка коммита.");
        }
        return null;
    }
}
