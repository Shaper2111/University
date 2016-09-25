package com.haulmont.testtask.models;

public abstract class Entity {

    private Long Id;

    Entity() {
    }

    protected Entity(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
