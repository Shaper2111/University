package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.views.Student.IStudentViewListener;
import com.haulmont.testtask.views.Student.StudentView;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class DeleteStudentWindow extends DeleteStudentWindowDesign {
    private StudentView view;
    private Student student;

    public DeleteStudentWindow(Object obj, StudentView view) {
        this.view = view;
        student = (Student) obj;
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
        for (IStudentViewListener listener: view.getListeners())
            listener.processData(student);
        this.view.getDeleteWindow().close();

    }

    private void DeleteCancelButtonClick(Button.ClickEvent event){
        this.view.getDeleteWindow().close();
    }
}
