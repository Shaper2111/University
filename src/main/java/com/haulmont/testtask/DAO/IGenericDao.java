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


interface IGenericDao<T extends Entity> {

    T create() throws DaoException;

    T get(int Id) throws DaoException;

    void update(T object) throws DaoException;

    void delete(T object) throws DaoException;

}