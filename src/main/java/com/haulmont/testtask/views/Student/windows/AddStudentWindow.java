package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.views.Student.IStudentViewListener;
import com.haulmont.testtask.views.Student.StudentView;
import com.haulmont.testtask.views.Student.forms.StudentForm;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class AddStudentWindow extends AddStudentWindowDesign {
    private StudentView view;
    private StudentForm form;

    public AddStudentWindow(StudentView view) {
        this.view = view;

        form = new StudentForm(this.view.getGroupsForSelect());
        addComponentAsFirst(form);

        addOkButton.addClickListener(this::AddOkButtonClick);
        addCancelButton.addClickListener
                (this::AddCancelButtonClick);

        this.view.getAddNewWindow().setContent(this);
        UI.getCurrent().addWindow(this.view.getAddNewWindow());
    }

    private void AddOkButtonClick(Button.ClickEvent event){
        BeanItem item = form.commit();
        if (item == null)
            return;
        for (IStudentViewListener listener: view.getListeners())
            listener.processData(item);
        this.view.getAddNewWindow().close();
    }

    private void AddCancelButtonClick(Button.ClickEvent event){
        this.view.getAddNewWindow().close();
    }
}
