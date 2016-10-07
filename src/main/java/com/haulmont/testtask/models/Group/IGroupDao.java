package com.haulmont.testtask.models.Group;

import com.haulmont.testtask.models.db.IGenericDao;
import com.haulmont.testtask.models.db.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface IGroupDao<PK extends Serializable>
        extends IGenericDao<Group, PK> {

    List<Group> getAll() throws DaoException;

    List<Integer> getNumbers() throws
            DaoException;

    Integer existsNumber(Integer number) throws
            DaoException;
}
