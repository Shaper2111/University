package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.views.Group.GroupView;
import com.haulmont.testtask.views.Group.IGroupViewListener;
import com.haulmont.testtask.views.Group.forms.GroupForm;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class AddGroupWindow extends AddGroupWindowDesign {
    private GroupView view;
    private GroupForm form;

    public AddGroupWindow(GroupView view) {
        this.view = view;

        form = new GroupForm();
        addComponentAsFirst(form);

        addOkButton.addClickListener(this::AddOkButtonClick);
        addCancelButton.addClickListener
                (this::AddCancelButtonClick);

        view.getAddNewWindow().setContent(this);
        UI.getCurrent().addWindow(this.view.getAddNewWindow());
    }

    private void AddOkButtonClick(Button.ClickEvent event){
        BeanItem item = form.commit();
        if (item == null)
            return;
        for (IGroupViewListener listener: view.getListeners())
            listener.processData(item);
        view.getAddNewWindow().close();
    }

    private void AddCancelButtonClick(Button.ClickEvent event){
        view.getAddNewWindow().close();
    }
}
