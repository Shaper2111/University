package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.views.Group.forms.GroupForm;
import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

import java.util.function.Consumer;

public class AddGroupWindow extends ModalWindow {

    private final GroupForm form;

    private final Consumer<BeanItem<Group>> cons;

    public AddGroupWindow(Consumer<BeanItem<Group>> cons) {
        super("Добавление группы");
        this.cons = cons;

        form = new GroupForm();
        design.addComponentAsFirst(form);
        setWindow();
    }

    @Override
    protected void OkButtonClick(Button.ClickEvent event) {
        BeanItem<Group> item = form.commit();
        if (item == null){
            Notification.show("Ошибка в заполнении данных.");
            return;
        }
        cons.accept(item);
        this.close();
    }
}
