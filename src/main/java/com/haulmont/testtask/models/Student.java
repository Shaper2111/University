/*
 * %W% %E% Firstname Lastname
 *
 * Copyright (c) 2016.
 *
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.haulmont.testtask.models;

import java.util.Date;

public class Student extends Entity {

    private String firstName;

    private String lastName;

    private String midName;

    private Date birthDate;

    private Integer groupNumber;

    public Student(Long id, String firstName, String lastName,
                   String midName, Date birthDate, Integer groupNumber) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.birthDate = birthDate;
        this.groupNumber = groupNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    void setMidName(String midName) {
        this.midName = midName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }
}
