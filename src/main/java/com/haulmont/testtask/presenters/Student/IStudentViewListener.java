package com.haulmont.testtask.presenters.Student;

import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.presenters.IViewListener;
import com.vaadin.data.util.BeanItem;

/**
 * Interface for Student presenter. Describes Student-specific
 * methods.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public interface IStudentViewListener extends IViewListener {
    void filterData(String lastName, Object groupNumber);
    void createData(BeanItem<Student> item);
    void updateData(BeanItem<Student> item);
    void deleteData(BeanItem<Student> item);
}
