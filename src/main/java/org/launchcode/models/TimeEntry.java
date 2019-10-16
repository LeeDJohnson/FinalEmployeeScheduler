package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class TimeEntry {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String clockIn;

    @NotNull
    private String clockOut;

    @ManyToOne
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClockIn() {
        return clockIn;
    }

    public void setClockIn(String clockIn) {
        this.clockIn = clockIn;
    }

    public String getClockOut() {
        return clockOut;
    }

    public void setClockOut(String clockOut) {
        this.clockOut = clockOut;
    }

    public TimeEntry() {};

    public TimeEntry(String clockIn, String clockOut, Employee employee) {
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.employee = employee;
    }

}

