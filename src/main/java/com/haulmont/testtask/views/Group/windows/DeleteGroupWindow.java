package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.views.Group.GroupView;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class DeleteGroupWindow extends DeleteGroupWindowDesign {
    private GroupView view;

    public DeleteGroupWindow(Object obj, GroupView view) {
        this.view = view;
        label.setValue("Вы действительно хотите удалить эту" +
                " группу?");

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
