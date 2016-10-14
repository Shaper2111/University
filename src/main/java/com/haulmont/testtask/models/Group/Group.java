package com.haulmont.testtask.models.Group;

import com.haulmont.testtask.models.Entity;


/**
 * Group entity in POJO realization.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class Group extends Entity {

    private Integer number;

    private String department;

    public Group(){
        super();
    }

    public Group(Long id, Integer number) {
        super(id);
        this.number = number;
    }

    public Group(Long id, Integer number, String department) {
        super(id);
        this.number = number;
        this.department = department;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
