package com.haulmont.testtask.views.Student.forms;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.views.Main.forms.Form;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;



public class StudentForm extends Form<Student> {

    private TextField id = new TextField("Идентификатор:");

    private TextField firstName = new TextField("Имя:");

    private TextField midName = new TextField("Отчество:");

    private TextField lastName = new TextField("Фамилия:");

    private DateField birthDate = new DateField("Дата рождения:");

    private ComboBox groupNumber = new ComboBox("Номер группы:");

    public StudentForm(IndexedContainer groups){
        super();
        validateFields(groups);
    }

    public StudentForm(Item item, IndexedContainer groups){
        super(item);
        validateFields(groups);
    }

    private void validateId() {
        id.setNullRepresentation("");
        id.setReadOnly(true);
        form.addComponent(id);
    }

    private void validateFirstName(){
        firstName.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        firstName.addValidator(new StringLengthValidator("Введите " +
                "до 20 символов.", 1, 20, false));
        firstName.setValidationVisible(false);
        firstName.setImmediate(true);
        firstName.setNullRepresentation("");
        form.addComponent(firstName);
    }

    private void validateMidName(){
        midName.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        midName.addValidator(new StringLengthValidator("Введите " +
                "до 30 символов.", 1, 30, false));
        midName.setValidationVisible(false);
        midName.setImmediate(true);
        midName.setNullRepresentation("");
        form.addComponent(midName);
    }

    private void validateLastName(){
        lastName.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        lastName.addValidator(new StringLengthValidator("Введите " +
                "до 50 символов.", 1, 50, false));
        lastName.setValidationVisible(false);
        lastName.setImmediate(true);
        lastName.setNullRepresentation("");
        form.addComponent(lastName);
    }

    private void validateBirthDate(){
        birthDate.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        birthDate.setImmediate(true);
        birthDate.setValidationVisible(false);
        form.addComponent(birthDate);
    }

    private void validateGroupNumber(IndexedContainer groups) {
        groupNumber.addValidator(new NullValidator
                ("Это поле обязательно.", false));
        groupNumber.setNullSelectionAllowed(false);
        groupNumber.setContainerDataSource(groups);
        groupNumber.setValue(binder.getItemDataSource()
                .getItemProperty("groupNumber").getValue());
        groupNumber.setValidationVisible(false);
        form.addComponent(groupNumber);
    }

    private void validateFields(IndexedContainer groups){
        binder.bindMemberFields(this);
        validateId();
        validateFirstName();
        validateMidName();
        validateLastName();
        validateBirthDate();
        validateGroupNumber(groups);
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
            Notification.show("Ошибка в заполнении данных.");
        }
        return null;
    }
}
