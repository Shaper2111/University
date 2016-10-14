package com.haulmont.testtask.models.Student;

import com.haulmont.testtask.models.Entity;

import java.util.Date;


/**
 * Student entity in POJO realization.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public class Student extends Entity {

    private String firstName;

    private String midName;

    private String lastName;

    private Date birthDate;

    private Integer groupNumber;

    public Student(){
        super();
    }

    public Student(Long id, String firstName, String lastName,
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }
}
