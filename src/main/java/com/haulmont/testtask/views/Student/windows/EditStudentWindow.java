package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.views.Student.IStudentViewListener;
import com.haulmont.testtask.views.Student.StudentView;
import com.haulmont.testtask.views.Student.forms.StudentForm;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class EditStudentWindow extends EditStudentWindowDesign {
    private StudentView view;
    private StudentForm form;

    public EditStudentWindow(Item item, StudentView view) {
        this.view = view;

        form = new StudentForm(item, this.view.getGroupsForSelect());
        addComponentAsFirst(form);

        editOkButton.addClickListener
                (this::EditOkButtonClick);
        editCancelButton.addClickListener
                (this::EditCancelButtonClick);

        this.view.getEditWindow().setContent(this);
        UI.getCurrent().addWindow(this.view.getEditWindow());
    }

    private void EditOkButtonClick(Button.ClickEvent event){
        BeanItem item = form.commit();
        if (item == null)
            return;
        for (IStudentViewListener listener: view.getListeners())
            listener.processData(item);
        this.view.getEditWindow().close();

    }

    private void EditCancelButtonClick(Button.ClickEvent event){
        this.view.getEditWindow().close();
    }
}
