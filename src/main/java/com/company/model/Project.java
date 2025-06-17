package com.company.model;

import java.time.LocalDate;

/**
 * Represents a project with a name, budget, and status.
 */
import java.time.LocalDate;

public class Project {
    private final String name;
    private final double budget;
    private final String status; // "active", "completed", etc.
    private final String manager;
    private final String type; // e.g., "internal", "external"
    private final LocalDate deadline;

    public Project(String name, double budget, String status, String manager, String type, LocalDate deadline) {
        this.name = name;
        this.budget = budget;
        this.status = status;
        this.manager = manager;
        this.type = type;
        this.deadline = deadline;
    }

    // Getters
    public String getName() { return name; }
    public double getBudget() { return budget; }
    public String getStatus() { return status; }
    public String getManager() { return manager; }
    public String getType() { return type; }
    public LocalDate getDeadline() { return deadline; }

    @Override
    public String toString() {
        return name + " (" + status + ", Budget: " + budget + ")";
    }
}
