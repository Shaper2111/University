package com.haulmont.testtask.models.Student;

import com.haulmont.testtask.models.Entity;

import java.util.Date;


public class Student extends Entity {

    private String firstName;

    private String midName;

    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() { return lastName; }

    void setLastName(String lastName) {
        this.lastName = lastName;
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
