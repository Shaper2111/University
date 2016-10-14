package com.haulmont.testtask.views.Student.windows;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.views.Main.windows.ModalWindow;
import com.haulmont.testtask.views.Student.forms.StudentForm;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;

import java.util.function.Consumer;

/**
 * Modal window for editing existing Student element in grid.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class EditStudentWindow extends ModalWindow {

    private final StudentForm form;

    private final Consumer<BeanItem<Student>> cons;

    public EditStudentWindow(Item item, IndexedContainer groups,
                             Consumer<BeanItem<Student>> cons) {
        super("Редактирование профиля");
        this.cons = cons;

        form = new StudentForm(item, groups);
        design.addComponentAsFirst(form);
        setWindow();
    }

    @Override
    protected void OkButtonClick(Button.ClickEvent event) {
        BeanItem<Student> item = form.commit();
        if (item == null)
            return;
        cons.accept(item);
        close();
    }
}
