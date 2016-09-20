/*
 * %W% %E% Firstname Lastname
 *
 * Copyright (c) 2016.
 *
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.haulmont.testtask.DAO;

import com.haulmont.testtask.DAO.exceptions.DaoException;
import com.haulmont.testtask.models.Entity;

abstract class GenericDao<T extends Entity> implements IGenericDao<T> {

    @Override
    public T create() throws DaoException {
        return null;
    }

    @Override
    public T get(int Id) throws DaoException {
        return null;
    }

    @Override
    public void update(T object) throws DaoException {

    }

    @Override
    public void delete(T object) throws DaoException {

    }
}
