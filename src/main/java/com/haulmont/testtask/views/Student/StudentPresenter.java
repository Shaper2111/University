package com.haulmont.testtask.views.Student;

import com.haulmont.testtask.models.Student.IStudentDao;
import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.models.db.Factory;
import com.haulmont.testtask.models.db.exceptions.DaoException;
import com.vaadin.data.util.BeanItemContainer;

class StudentPresenter implements IStudentViewListener {
    private IStudentDao dao;

    private StudentView view;

    StudentPresenter(StudentView view){
        this.view = view;
        this.dao = Factory.getStudentDao();
    }

    public void showData(){
        BeanItemContainer<Student> container =
                new BeanItemContainer<>(Student.class);
        try {
            for (Object o: dao.getAll())
                container.addBean((Student) o);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        view.generateGrid(container);
    }
}
