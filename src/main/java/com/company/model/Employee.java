package com.company.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents an employee with a name, department, and assigned projects.
 */
public class Employee {

    private final String name;
    private final String department;
    private final List<Project> projects;

    public Employee(String name, String department, List<Project> projects) {
        this.name = name;
        this.department = department;
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<Project> getProjects() {
        return projects;
    }
}


