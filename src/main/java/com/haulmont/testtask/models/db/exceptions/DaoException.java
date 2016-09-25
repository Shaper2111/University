package com.haulmont.testtask.models.db.exceptions;

public class DaoException extends Exception {

    public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String msg, Exception e) {
        super(msg, e);
    }
}
