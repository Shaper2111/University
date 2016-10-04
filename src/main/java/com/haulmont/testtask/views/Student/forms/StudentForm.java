package com.haulmont.testtask.views.Student.forms;

import com.haulmont.testtask.models.Student.Student;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;


public class StudentForm extends CustomComponent {

    private FormLayout form;

    private FieldGroup binder;

    private TextField id = new TextField("Идентификатор:");

    private TextField firstName = new TextField("Имя:");

    private TextField midName = new TextField("Отчество:");

    private TextField lastName = new TextField("Фамилия:");

    private DateField birthDate = new DateField("Дата рождения:");

    private ComboBox groupNumber = new ComboBox("Номер группы:");

    public StudentForm(BeanItemContainer groups){

        BeanItem<Student> item = new BeanItem<>(new Student());
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);

        generateForm(groups);

        setCompositionRoot(form);
    }

    public StudentForm(Item item, BeanItemContainer groups){

        binder = new FieldGroup(item);
        binder.bindMemberFields(this);

        generateForm(groups);

        setCompositionRoot(form);
    }

    private void generateForm(BeanItemContainer groups){

        form = new FormLayout();
        form.setMargin(false);
        form.setSizeUndefined();

        validateId();
        validateFirstName();
        validateMidName();
        validateLastName();
        validateBirthDate();
        validateGroupNumber(groups);
    }

    private void validateId() {
        id.setNullRepresentation("");
        id.setReadOnly(true);
        form.addComponent(id);
    }

    private void validateFirstName(){
        firstName.setRequired(true);
        firstName.addValidator(new StringLengthValidator("Введите " +
                "до 20 символов.", 1, 20, false));
        firstName.setImmediate(true);
        firstName.setNullRepresentation("");
        form.addComponent(firstName);
    }

    private void validateMidName(){
        midName.setRequired(true);
        midName.addValidator(new StringLengthValidator("Введите " +
                "до 30 символов.", 1, 30, false));
        midName.setImmediate(true);
        midName.setNullRepresentation("");
        form.addComponent(midName);
    }

    private void validateLastName(){
        lastName.setRequired(true);
        lastName.addValidator(new StringLengthValidator("Введите " +
                "до 50 символов.", 1, 50, false));
        lastName.setImmediate(true);
        lastName.setNullRepresentation("");
        form.addComponent(lastName);
    }

    private void validateBirthDate(){
        birthDate.setRequired(true);
        birthDate.setImmediate(true);
        form.addComponent(birthDate);
    }

    private void validateGroupNumber(BeanItemContainer groups) {
        groupNumber.setRequired(true);
        groupNumber.addValidator(new NullValidator
                ("Это поле обязательно.", false));
        groupNumber.setNullSelectionAllowed(false);
        groupNumber.setContainerDataSource(groups);

        groupNumber.setItemCaptionPropertyId("number");
        form.addComponent(groupNumber);
    }
}
