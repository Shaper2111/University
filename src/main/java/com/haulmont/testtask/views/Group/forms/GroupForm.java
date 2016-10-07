package com.haulmont.testtask.views.Group.forms;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.views.Main.forms.Form;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;

public class GroupForm extends Form<Group> {

    private TextField id = new TextField("Идентификатор:");

    private TextField number = new TextField("Номер группы:");

    private TextField department = new TextField("Факультет:");

    public GroupForm() {
        super();
        generateFields();
    }

    public GroupForm(Item item) {
        super(item);
        generateFields();
    }

    private void generateFields(){
        binder.bindMemberFields(this);
        generateId();
        generateNumber();
        generateDepartment();
    }

    @Override
    public BeanItem<Group> createItem() {
        return new BeanItem<>(new Group());
    }

    private void generateId(){
        id.setNullRepresentation("");
        id.setReadOnly(true);
        form.addComponent(id);
    }

    private void generateNumber(){
        number.addValidator(new NullValidator("Это поле обязательно",
                false));
        number.addValidator(new IntegerRangeValidator("Введите до 4" +
                " цифр.", 1, 9999));
        number.setConversionError("Введите только цифры.");
        number.setValidationVisible(false);
        number.setImmediate(true);
        number.setNullRepresentation("");
        form.addComponent(number);
    }

    private void generateDepartment(){
        department.addValidator(new NullValidator("Это поле " +
                "обязательно", false));
        department.addValidator(new StringLengthValidator("Введите " +
                "до 40 символов.", 1, 40, false));
        department.setValidationVisible(false);
        department.setImmediate(true);
        department.setNullRepresentation("");
        form.addComponent(department);
    }

    public BeanItem<Group> commit() {
        try {
            binder.commit();
            return getItem();
        } catch (FieldGroup.CommitException e) {
            number.setValidationVisible(true);
            department.setValidationVisible(true);
            Notification.show("Ошибка в заполнении данных.");
        }
        return null;
    }
}
