package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.views.Group.GroupView;
import com.haulmont.testtask.views.Group.forms.GroupForm;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class EditGroupWindow extends EditGroupWindowDesign {
    private GroupView view;
    private GroupForm form;

    public EditGroupWindow(Item item, GroupView view) {
        this.view = view;

        form = new GroupForm(item);
        addComponentAsFirst(form);

        editOkButton.addClickListener
                (this::EditOkButtonClick);
        editCancelButton.addClickListener
                (this::EditCancelButtonClick);

        this.view.getEditWindow().setContent(this);
        UI.getCurrent().addWindow(this.view.getEditWindow());
    }

    private void EditOkButtonClick(Button.ClickEvent event){
        form.commit();
        this.view.getEditWindow().close();

    }

    private void EditCancelButtonClick(Button.ClickEvent event){
        this.view.getEditWindow().close();
    }
}
