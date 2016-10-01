package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.ui.Button;

public class AddGroupWindow extends ModalWindow {

    AddGroupWindowDesign content = new AddGroupWindowDesign();

    public AddGroupWindow(String caption) {
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
