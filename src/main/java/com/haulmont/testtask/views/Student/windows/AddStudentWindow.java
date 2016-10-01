package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.ui.Button;

public class AddStudentWindow extends ModalWindow {

    AddStudentWindowDesign content = new AddStudentWindowDesign();

    public AddStudentWindow(String caption) {
        super(caption);
        content.addOkButton.addClickListener(this::AddOkButtonClick);
        content.addCancelButton.addClickListener
                (this::AddCancelButtonClick);
        this.setContent(content);
    }

    private void AddOkButtonClick(Button.ClickEvent event){
        this.close();

    }

    private void AddCancelButtonClick(Button.ClickEvent event){
        this.close();
    }
}
