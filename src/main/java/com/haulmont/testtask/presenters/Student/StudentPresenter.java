package com.haulmont.testtask.presenters.Student;

import com.haulmont.testtask.models.Student.IStudentDao;
import com.haulmont.testtask.models.Student.Student;
import com.haulmont.testtask.models.db.Factory;
import com.haulmont.testtask.models.db.exceptions.DaoException;
import com.haulmont.testtask.views.Student.StudentView;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.Renderer;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class StudentPresenter implements IStudentViewListener {

    private final IStudentDao<Student> dao;

    private final StudentView view;

    public StudentPresenter(StudentView view){
        this.view = view;
        this.dao = Factory.getStudentDao();
    }

    private IndexedContainer getGroupsForSelect() {
        try {
            return new IndexedContainer(dao.getGroupsForSelect());
        } catch (DaoException e) {
            view.createNotify("Ошибка при получении списка групп: " +
                    e.getMessage());
        }
        return null;
    }

    @Override
    public void showData(){

        view.setGroups(getGroupsForSelect());

        BeanItemContainer<Student> container =
                new BeanItemContainer<>(Student.class);
        try {
            container.addAll(dao.getAll());
        } catch (DaoException e) {
            view.createNotify("Ошибка: " + e.getMessage());
        }
        view.generateGrid(container, setColumns(), setRenderers());

    }

    private HashMap<String, String> setColumns(){
        HashMap<String, String> columns = new LinkedHashMap<>();
        columns.put("id", "Идентификатор");
        columns.put("firstName", "Имя");
        columns.put("midName", "Отчество");
        columns.put("lastName", "Фамилия");
        columns.put("birthDate", "Дата рождения");
        columns.put("groupNumber", "Номер группы");
        return columns;
    }

    private HashMap<String, Renderer<?>> setRenderers(){
        HashMap<String, Renderer<?>> renderers = new HashMap<>();
        DateFormat format = DateFormat.getDateInstance();
        renderers.put("birthDate", new DateRenderer(format));
        return renderers;
    }

    @Override
    public void filterData(String lastName, Object groupNumber) {
        BeanItemContainer<Student> container = new
                BeanItemContainer<>(Student.class);
        try {
            container.addAll(dao.getStudentsBy(lastName,groupNumber));
            view.filterData(container);
        } catch (DaoException e) {
            view.createNotify("Ошибка фильтра: " + e.getMessage());
        }
    }

    @Override
    public void createData(BeanItem<Student> item) {
        Student student = item.getBean();
        try {
            student.setId(dao.create(student));
            view.addElementToGrid(item);
            view.createNotify("Профиль успешно добавлен.");
        } catch (DaoException e) {
            view.createNotify("Ошибка создания: " + e.getMessage());
        }
    }

    @Override
    public void updateData(BeanItem<Student> item) {
        Student student = item.getBean();
        try {
            dao.update(student);
            view.createNotify("Профиль успешно обновлен.");
        } catch (DaoException e) {
            view.createNotify("Ошибка обновления: " + e.getMessage());
        }
    }

    @Override
    public void deleteData(BeanItem<Student> item) {
        try {
            dao.delete(item.getBean().getId());
            view.removeElementFromGrid(item);
            view.createNotify("Профиль успешно удален.");
        } catch (DaoException e) {
            view.createNotify("Ошибка удаления: " + e.getMessage());
        }
    }
}
