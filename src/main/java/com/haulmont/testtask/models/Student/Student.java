package com.haulmont.testtask.models.Student;

import com.haulmont.testtask.models.Entity;

import java.util.Date;

class Student extends Entity {

    private String firstName;

    private String lastName;

    private String midName;

    private Date birthDate;

    private Integer groupNumber;

    Student(Long id, String firstName, String lastName,
            String midName, Date birthDate, Integer groupNumber) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.birthDate = birthDate;
        this.groupNumber = groupNumber;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getMidName() {
        return midName;
    }

    void setMidName(String midName) {
        this.midName = midName;
    }

    Date getBirthDate() {
        return birthDate;
    }

    void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    Integer getGroupNumber() {
        return groupNumber;
    }

    void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }
}
