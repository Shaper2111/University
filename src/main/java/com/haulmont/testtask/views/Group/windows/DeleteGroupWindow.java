package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.views.Group.GroupView;
import com.haulmont.testtask.views.Group.IGroupViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class DeleteGroupWindow extends DeleteGroupWindowDesign {
    private GroupView view;
    private Group group;

    public DeleteGroupWindow(Object obj, GroupView view) {
        this.view = view;
        group = (Group) obj;
        label.setValue("Вы действительно хотите удалить эту" +
                " группу?");

        deleteOkButton.addClickListener
                (this::DeleteOkButtonClick);
        deleteCancelButton.addClickListener
                (this::DeleteCancelButtonClick);

        view.getDeleteWindow().setContent(this);
        UI.getCurrent().addWindow(view.getDeleteWindow());
    }

    private void DeleteOkButtonClick(Button.ClickEvent event){
        for (IGroupViewListener listener: view.getListeners())
            listener.processData(group);
        view.getDeleteWindow().close();
    }

    private void DeleteCancelButtonClick(Button.ClickEvent event){
        view.getDeleteWindow().close();
    }
}
