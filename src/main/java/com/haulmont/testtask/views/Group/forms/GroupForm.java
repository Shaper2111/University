package com.haulmont.testtask.views.Group.forms;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class GroupForm extends CustomComponent {

    private FormLayout form;

    private TextField number = new TextField("Номер группы");

    private TextField department = new TextField("Факультет");

    public GroupForm(){
        form = new FormLayout();
        validateNumber();
        validateDepartment();
        setCompositionRoot(form);
    }

    public GroupForm(Item item){
        form = new FormLayout();
        validateNumber();
        validateDepartment();
        FieldGroup binder = new FieldGroup(item);
        binder.bindMemberFields(this);
        setCompositionRoot(form);
    }

    private void validateNumber(){
        number.setRequired(true);
        number.addValidator(new NullValidator("Это поле обязательно."
                , false));
        number.addValidator(new IntegerRangeValidator("Введите 4 " +
                "цифры."
                , 1, 9999));
        number.setImmediate(true);
        form.addComponent(number);
    }

    private void validateDepartment(){
        department.setRequired(true);
        department.addValidator(new StringLengthValidator("Введите " +
                "до 40 символов.", 1, 40, false));
        department.setImmediate(true);
        form.addComponent(department);
    }
}
