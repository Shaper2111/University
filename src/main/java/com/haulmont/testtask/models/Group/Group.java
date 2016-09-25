package com.haulmont.testtask.models.Group;

import com.haulmont.testtask.models.Entity;

class Group extends Entity {

    private Integer Number;

    private String Department;

    Group(Long id, Integer number, String department) {
        super(id);
        Number = number;
        Department = department;
    }

    Integer getNumber() {
        return Number;
    }

    void setNumber(Integer number) {
        Number = number;
    }

    String getDepartment() {
        return Department;
    }

    void setDepartment(String department) {
        Department = department;
    }
}
