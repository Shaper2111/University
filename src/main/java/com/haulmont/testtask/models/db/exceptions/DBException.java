package com.haulmont.testtask.models.db.exceptions;

/**
 * General exception class that handles errors with database.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class DBException extends Exception {


    public DBException(String msg) {
        super(msg);
    }

    public DBException(String msg, Exception e) {
        super(msg, e);
    }
}
