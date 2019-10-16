package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Date {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=50)
    private String name;

    @ManyToMany
    private List<Employee> employees = new ArrayList<>();

    public void addItem(Employee item) {
        employees.add(item);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Date() {};

    public Date(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
