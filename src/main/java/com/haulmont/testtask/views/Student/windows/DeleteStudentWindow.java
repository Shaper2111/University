package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Label;

import java.util.function.Consumer;

public class DeleteStudentWindow extends ModalWindow {

    private final BeanItem<Student> student;

    private final Consumer<BeanItem<Student>> cons;

    public DeleteStudentWindow(Object obj,
                               Consumer<BeanItem<Student>> cons) {
        super("Удаление профиля");
        this.student = new BeanItem<>((Student) obj);
        this.cons = cons;

        Label label = new Label("Вы действительно хотите удалить " +
                "этот профиль?");
        design.addComponentAsFirst(label);
        setWindow();
    }

    @Override
    protected void OkButtonClick() {
        cons.accept(student);
        close();
    }
}
