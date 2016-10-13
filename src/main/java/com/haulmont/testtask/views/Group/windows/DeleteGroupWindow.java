package com.haulmont.testtask.views.Group.windows;

import com.haulmont.testtask.models.Group.Group;
import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

import java.util.function.Consumer;

public class DeleteGroupWindow extends ModalWindow {

    private final BeanItem<Group> group;

    private final Consumer<BeanItem<Group>> cons;

    public DeleteGroupWindow(Object obj,
                             Consumer<BeanItem<Group>> cons) {
        super("Удаление группы");
        this.group = new BeanItem<>((Group) obj);
        this.cons = cons;

        Label label = new Label("Вы действительно хотите удалить " +
                "эту группу?");
        design.addComponentAsFirst(label);
        setWindow();
    }

    @Override
    protected void OkButtonClick(Button.ClickEvent event) {
        cons.accept(group);
        close();
    }
}
