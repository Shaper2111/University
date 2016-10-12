package com.haulmont.testtask.views.Student.forms;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.views.Main.forms.Form;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;



public class StudentForm extends Form<Student> {

    private final TextField id = new TextField("Идентификатор:");

    private final TextField firstName = new TextField("Имя:");

    private final TextField midName = new TextField("Отчество:");

    private final TextField lastName = new TextField("Фамилия:");

    private final DateField birthDate = new DateField("Дата рождения:");

    private final ComboBox groupNumber = new ComboBox("Номер группы:");

    public StudentForm(IndexedContainer groups){
        super();
        generateFields(groups);
    }

    public StudentForm(Item item, IndexedContainer groups){
        super(item);
        generateFields(groups);
    }

    private void generateId() {
        id.setNullRepresentation("");
        id.setReadOnly(true);
        form.addComponent(id);
    }

    private void generateFirstName(){
        firstName.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        firstName.addValidator(new StringLengthValidator("Введите " +
                "до 20 символов.", 1, 20, false));
        firstName.setValidationVisible(false);
        firstName.setImmediate(true);
        firstName.setNullRepresentation("");
        form.addComponent(firstName);
    }

    private void generateMidName(){
        midName.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        midName.addValidator(new StringLengthValidator("Введите " +
                "до 30 символов.", 1, 30, false));
        midName.setValidationVisible(false);
        midName.setImmediate(true);
        midName.setNullRepresentation("");
        form.addComponent(midName);
    }

    private void generateLastName(){
        lastName.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        lastName.addValidator(new StringLengthValidator("Введите " +
                "до 50 символов.", 1, 50, false));
        lastName.setValidationVisible(false);
        lastName.setImmediate(true);
        lastName.setNullRepresentation("");
        form.addComponent(lastName);
    }

    private void generateBirthDate(){
        birthDate.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        birthDate.setImmediate(true);
        birthDate.setValidationVisible(false);
        form.addComponent(birthDate);
    }

    private void generateGroupNumber(IndexedContainer groups) {
        groupNumber.addValidator(new NullValidator
                ("Это поле обязательно.", false));
        groupNumber.setNullSelectionAllowed(false);
        groupNumber.setContainerDataSource(groups);
        groupNumber.setValue(binder.getItemDataSource()
                .getItemProperty("groupNumber").getValue());
        groupNumber.setValidationVisible(false);
        form.addComponent(groupNumber);
    }

    private void generateFields(IndexedContainer groups){
        binder.bindMemberFields(this);
        generateId();
        generateFirstName();
        generateMidName();
        generateLastName();
        generateBirthDate();
        generateGroupNumber(groups);
    }

    @Override
    public BeanItem<Student> createItem() {
        return new BeanItem<>(new Student());
    }

    @Override
    public BeanItem<Student> commit() {
        try {
            binder.commit();
            return getItem();
        } catch (FieldGroup.CommitException e) {
            firstName.setValidationVisible(true);
            midName.setValidationVisible(true);
            lastName.setValidationVisible(true);
            birthDate.setValidationVisible(true);
            groupNumber.setValidationVisible(true);
        }
        return null;
    }
}
