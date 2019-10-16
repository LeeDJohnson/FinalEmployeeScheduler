package org.launchcode.models.forms;

import org.launchcode.models.Employee;
import org.launchcode.models.Date;
import javax.validation.constraints.NotNull;

public class AddDateItemForm {

    private Date date;

    private Iterable<Employee> employees;

    @NotNull
    private int dateId;

    @NotNull
    private int employeeId;

    public AddDateItemForm() {}

    public AddDateItemForm(Date date, Iterable<Employee> employees) {
        this.date = date;
        this.employees = employees;
    }


    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Iterable<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Iterable<Employee> employees) {
        this.employees = employees;
    }
}
