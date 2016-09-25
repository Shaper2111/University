package com.haulmont.testtask.models.db.exceptions;

/**
 * General exception class that handles errors with database.
 */
public class DBException extends Exception {

    public DBException(String msg, Exception e) {
        super(msg, e);
    }
}
