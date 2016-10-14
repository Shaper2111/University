package com.haulmont.testtask.models.db.exceptions;

/**
 * Basic exception class for waist all other exceptions.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class DaoException extends Exception {

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Exception e) { super(e.getMessage()); }

    public DaoException(String msg, Exception e) {
        super(msg, e);
    }
}
