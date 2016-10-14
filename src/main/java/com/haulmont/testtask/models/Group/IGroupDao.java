package com.haulmont.testtask.models.Group;

import com.haulmont.testtask.models.Entity;
import com.haulmont.testtask.models.db.IGenericDao;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.util.List;

/**
 * DAO interface for all Group CRUD operations.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 * @param <Group> Group POJO
 */
public interface IGroupDao<Group extends Entity>
        extends IGenericDao<Group, Long> {

    List<Group> getAll() throws DaoException;

    List<Integer> getNumbers() throws
            DaoException;
}
