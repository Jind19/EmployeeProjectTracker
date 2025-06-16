package com.company.model;

/**
 * Represents a project with a name, budget, and status.
 */
public class Project {
    private final String name;
    private final double budget;
    private final String status; // e.g., "active", "completed"

    public Project(String name, double budget, String status) {
        this.name = name;
        this.budget = budget;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public String getStatus() {
        return status;
    }
}
