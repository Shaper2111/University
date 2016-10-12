package com.haulmont.testtask.models.Group;

import com.haulmont.testtask.models.Entity;
import com.haulmont.testtask.models.db.IGenericDao;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.util.List;

public interface IGroupDao<Group extends Entity>
        extends IGenericDao<Group, Long> {

    List<Group> getAll() throws DaoException;

    List<Integer> getNumbers() throws
            DaoException;

    Integer existsNumber(Integer number) throws
            DaoException;
}
