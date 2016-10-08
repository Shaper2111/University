package com.haulmont.testtask.views.Student;

import com.haulmont.testtask.models.Student.IStudentDao;
import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.models.db.Factory;
import com.haulmont.testtask.models.db.exceptions.DaoException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;

import java.util.HashMap;
import java.util.LinkedHashMap;

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
            view.createNotify("Ошибка: " + e.getMessage());
        }
        HashMap<String, String> columns = new LinkedHashMap<>();

        columns.put("id", "Идентификатор");
        columns.put("firstName", "Имя");
        columns.put("midName", "Отчество");
        columns.put("lastName", "Фамилия");
        columns.put("birthDate", "Дата рождения");
        columns.put("groupNumber", "Номер группы");

        view.generateGrid(container, columns);
    }

    @Override
    public void filterData(String lastName, Integer groupNumber) {
        BeanItemContainer<Student> container =
                new BeanItemContainer<>(Student.class);
        try {
            for (Object o: dao.getStudentsBy(lastName, groupNumber))
                container.addBean((Student) o);
        } catch (DaoException e) {
            view.createNotify("Ошибка: " + e.getMessage());
        }
        view.filterData(container);
    }

    @Override
    public IndexedContainer getGroupsForSelect() {
        try {
            return new IndexedContainer(dao.getGroupsForSelect());
        } catch (DaoException e) {
            view.createNotify("Ошибка: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void processData(BeanItem<Student> item) {
        Student student = item.getBean();
        if (student.getId() == null) {
            createData(student);
        } else {
            updateData(student);
        }
    }

    @Override
    public void processData(Student obj) {
        try {
            dao.delete(obj);
        } catch (DaoException e) {
            view.createNotify("Ошибка удаления: " + e.getMessage());
            return;
        }
        BeanItem<Student> item = new BeanItem<>(obj);
        view.removeElementFromGrid(item);
        view.createNotify("Профиль успешно удалена.");
    }

    private void createData(Student student){
        try {
            student.setId((Long) dao.create(student));
        } catch (DaoException e) {
            view.createNotify("Ошибка: " + e.getMessage());
            return;
        }
        BeanItem<Student> item = new BeanItem<>(student);
        view.addElementToGrid(item);
        view.createNotify("Профиль успешно добавлена.");
    }

    private void updateData(Student student){
        try {
            dao.update(student);
        } catch (DaoException e) {
            view.createNotify("Ошибка обновления: " + e.getMessage());
            return;
        }
        view.createNotify("Профиль успешно обновлена.");
    }

}
