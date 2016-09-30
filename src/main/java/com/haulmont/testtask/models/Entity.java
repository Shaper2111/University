package com.haulmont.testtask.models;

public abstract class Entity {

    private Long id;

    Entity() {
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
