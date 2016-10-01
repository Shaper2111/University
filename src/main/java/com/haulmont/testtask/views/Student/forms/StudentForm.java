package com.haulmont.testtask.views.Student.forms;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class StudentForm extends CustomComponent {

    private FormLayout form;

    private TextField firstName = new TextField("Имя");

    private TextField midName = new TextField("Отчество");

    private TextField lastName = new TextField("Фамилия");

    private DateField birthDate = new DateField("Дата рождения");

    private TextField groupNumber = new TextField("Номер группы");

    public StudentForm(){
        form = new FormLayout();
        validateFirstName();
        validateMidName();
        validateLastName();
        validateBirthDate();
        validateGroupNumber();
        setCompositionRoot(form);
    }

    public StudentForm(Item item){
        form = new FormLayout();
        validateFirstName();
        validateMidName();
        validateLastName();
        validateBirthDate();
        validateGroupNumber();
        FieldGroup binder = new FieldGroup(item);
        binder.bindMemberFields(this);
        setCompositionRoot(form);
    }

    private void validateFirstName(){
        firstName.setRequired(true);
        firstName.addValidator(new StringLengthValidator("Введите " +
                "до 40 символов.", 1, 40, false));
        firstName.setImmediate(true);
        form.addComponent(firstName);
    }

    private void validateMidName(){
        midName.setRequired(true);
        midName.addValidator(new StringLengthValidator("Введите " +
                "до 40 символов.", 1, 40, false));
        midName.setImmediate(true);
        form.addComponent(midName);
    }

    private void validateLastName(){
        lastName.setRequired(true);
        lastName.addValidator(new StringLengthValidator("Введите " +
                "до 40 символов.", 1, 40, false));
        lastName.setImmediate(true);
        form.addComponent(lastName);
    }

    private void validateBirthDate(){
        birthDate.setRequired(true);
        birthDate.setImmediate(true);
        form.addComponent(birthDate);
    }

    private void validateGroupNumber() {
        groupNumber.setRequired(true);
        groupNumber.addValidator(new NullValidator
                ("Это поле обязательно.", false));
        groupNumber.addValidator(new IntegerRangeValidator
                ("Введите 4 цифры.", 1, 9999));
        groupNumber.setImmediate(true);
        form.addComponent(groupNumber);
    }
}
