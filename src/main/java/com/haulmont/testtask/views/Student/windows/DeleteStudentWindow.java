package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.views.Student.StudentView;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class DeleteStudentWindow extends DeleteStudentWindowDesign {
    private StudentView view;

    public DeleteStudentWindow(Object obj, StudentView view) {
        this.view = view;

        label.setValue("Вы действительно хотите удалить " +
                "этот профиль?");

        deleteOkButton.addClickListener
                (this::DeleteOkButtonClick);
        deleteCancelButton.addClickListener
                (this::DeleteCancelButtonClick);

        this.view.getDeleteWindow().setContent(this);
        UI.getCurrent().addWindow(this.view.getDeleteWindow());
    }

    private void DeleteOkButtonClick(Button.ClickEvent event){
        this.view.getDeleteWindow().close();

    }

    private void DeleteCancelButtonClick(Button.ClickEvent event){
        this.view.getDeleteWindow().close();
    }
}
