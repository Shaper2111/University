package com.haulmont.testtask.models;

/**
 * Base abstract class for POJO.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public abstract class Entity {

    private Long id;

    protected Entity() {
    }

    protected Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
