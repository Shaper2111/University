package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.ui.Button;

public class DeleteGroupWindow extends ModalWindow {
    DeleteGroupWindowDesign content = new DeleteGroupWindowDesign();

    public DeleteGroupWindow(String caption, Object obj) {
        super(caption);
        content.label.setValue("Вы действительно хотите удалить эту" +
                " группу?");
        content.deleteOkButton.addClickListener
                (this::DeleteOkButtonClick);
        content.deleteCancelButton.addClickListener
                (this::DeleteCancelButtonClick);
        setContent(content);
    }

    private void DeleteOkButtonClick(Button.ClickEvent event){
        this.close();

    }

    private void DeleteCancelButtonClick(Button.ClickEvent event){
        this.close();
    }
}
