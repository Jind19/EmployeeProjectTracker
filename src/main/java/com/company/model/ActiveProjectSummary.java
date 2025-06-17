package com.company.model;

public class ActiveProjectSummary {
    private int count = 0;
    private double totalBudget = 0;

    public void add(Project project) {
        if ("active".equalsIgnoreCase(project.getStatus())) {
            count++;
            totalBudget += project.getBudget();
        }
    }

    public ActiveProjectSummary combine(ActiveProjectSummary other) {
        this.count += other.count;
        this.totalBudget += other.totalBudget;
        return this;
    }

    public int getCount() { return count; }
    public double getTotalBudget() { return totalBudget; }

    @Override
    public String toString() {
        return "Active Projects: " + count + ", Total Budget: " + totalBudget;
    }
}

