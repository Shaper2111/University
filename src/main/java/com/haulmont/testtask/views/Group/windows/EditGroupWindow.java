package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.views.Group.forms.GroupForm;
import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;

public class EditGroupWindow extends ModalWindow {
    public EditGroupWindowDesign content = new
            EditGroupWindowDesign();

    public EditGroupWindow(String caption, Item item) {
        super(caption);
        content.groupForm = new GroupForm(item);
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
