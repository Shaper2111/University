package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.haulmont.testtask.views.Student.forms.StudentForm;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;

public class EditStudentWindow extends ModalWindow {
    public EditStudentWindowDesign content = new
            EditStudentWindowDesign();

    public EditStudentWindow(String caption, Item item) {
        super(caption);
        content.form = new StudentForm(item);
        content.editOkButton.addClickListener
                (this::EditOkButtonClick);
        content.editCancelButton.addClickListener
                (this::EditCancelButtonClick);
        setContent(content);
    }

    private void EditOkButtonClick(Button.ClickEvent event){
        this.close();

    }

    private void EditCancelButtonClick(Button.ClickEvent event){
        this.close();
    }
}
