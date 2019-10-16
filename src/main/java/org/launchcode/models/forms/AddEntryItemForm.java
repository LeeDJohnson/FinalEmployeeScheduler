package org.launchcode.models.forms;

import org.hibernate.jdbc.Work;
import org.launchcode.models.Employee;
import org.launchcode.models.TimeEntry;
import org.launchcode.models.WorkHours;

import javax.validation.constraints.NotNull;


public class AddEntryItemForm {

    private String clockIn;

    private String clockOut;

    private Iterable<Employee> employees;

    public TimeEntry getTimeEntry() {
        return timeEntry;
    }

    public void setTimeEntry(TimeEntry timeEntry) {
        this.timeEntry = timeEntry;
    }

    private TimeEntry timeEntry;

    @NotNull
    private int workId;

    @NotNull
    private int employeeId;

    public AddEntryItemForm(Iterable<Employee> employees) {
        this.employees = employees;
    }

    public AddEntryItemForm() {}

    public AddEntryItemForm(Iterable<Employee> employees, TimeEntry timeEntry) {
        this.employees = employees;
        this.timeEntry = timeEntry;
    };

    public AddEntryItemForm(String clockIn, String clockOut, int employeeId) {
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.employeeId = employeeId;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Iterable<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Iterable<Employee> employees) {
        this.employees = employees;
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
}
