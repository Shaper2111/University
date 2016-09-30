package com.haulmont.testtask.models.Group;
import com.haulmont.testtask.models.Entity;


public class Group extends Entity {

    private Integer number;

    private String department;

    Group(Long id, Integer number, String department) {
        super(id);
        this.number = number;
        this.department = department;
    }

    public Integer getNumber() {
        return number;
    }

    void setNumber(Integer number) {
        this.number = number;
    }

    public String getDepartment() {
        return this.department;
    }

    void setDepartment(String department) {
        this.department = department;
    }
}
