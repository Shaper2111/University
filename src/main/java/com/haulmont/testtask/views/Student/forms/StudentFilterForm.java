package com.haulmont.testtask.views.Student.forms;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

import java.util.function.BiConsumer;

public class StudentFilterForm {

    private final Grid.HeaderRow row;

    private final TextField lastName = new TextField();

    private final TextField groupNumber = new TextField();

    private final Button button = new Button("Применить", (Button.ClickListener) (e) -> ButtonClick());

    private final BiConsumer<String, Object> cons;

    public StudentFilterForm(Grid grid, BiConsumer<String, Object> cons){
        row = grid.appendHeaderRow();
        this.cons = cons;

        generateLastName();
        generateNumber();
        row.getCell("id").setComponent(button);
    }

    private void generateLastName(){
        lastName.addValidator(new StringLengthValidator("Введите " +
                "до 50 символов.", 0, 50, true));
        lastName.setValidationVisible(false);
        lastName.setRequired(false);
        lastName.setNullRepresentation("");
        row.getCell("lastName").setComponent(lastName);
    }

    private void generateNumber(){
        groupNumber.setConverter(Integer.class);
        groupNumber.setConversionError("Введите только цифры.");
        groupNumber.addValidator(new IntegerRangeValidator("Введите" +
                " до 4 цифр.", 1, 9999));
        groupNumber.setValidationVisible(false);
        groupNumber.setRequired(false);
        groupNumber.setNullRepresentation(null);
        row.getCell("groupNumber").setComponent(groupNumber);
    }

    private void ButtonClick(){
        try {
            lastName.validate();
            groupNumber.validate();
            cons.accept(lastName.getValue(),
                    groupNumber.getConvertedValue());
        }catch (Validator.InvalidValueException ex){
            lastName.setValidationVisible(true);
            groupNumber.setValidationVisible(true);
        }
    }
}
