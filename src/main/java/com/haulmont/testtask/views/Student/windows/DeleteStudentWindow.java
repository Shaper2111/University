package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.ui.Button;

public class DeleteStudentWindow extends ModalWindow {
    DeleteStudentWindowDesign content
            = new DeleteStudentWindowDesign();

    public DeleteStudentWindow(String caption, Object obj) {
        super(caption);
        content.label.setValue("Вы действительно хотите удалить " +
                "этот профиль?");
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
