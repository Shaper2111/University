package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.haulmont.testtask.views.Student.forms.StudentForm;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

import java.util.function.Consumer;

public class AddStudentWindow extends ModalWindow {

    private final StudentForm form;

    private final Consumer<BeanItem<Student>> cons;

    public AddStudentWindow(IndexedContainer groups,
                            Consumer<BeanItem<Student>> cons) {
        super("Добавление профиля");
        this.cons = cons;

        form = new StudentForm(groups);
        design.addComponentAsFirst(form);
        setWindow();
    }

    @Override
    protected void OkButtonClick(Button.ClickEvent event) {
        BeanItem<Student> item = form.commit();
        if (item == null){
            Notification.show("Ошибка в заполнении данных.");
            return;
        }
        cons.accept(item);
        close();
    }
}
