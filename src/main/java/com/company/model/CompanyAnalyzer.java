package com.company.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CompanyAnalyzer {

    /**
     * Flattens the list of projects from a list of employees.
     * List<Employee> employees; // Each employee has a List<Project> projects.
     */
    public List<Project> getAllProjects(List<Employee> employees) {
        return employees.stream()
                .flatMap(employee -> employee.getProjects().stream())
                .collect(Collectors.toList());
    }

    /**
     * Groups projects by status and counts them.
     * GroupingBy creates a Map<String, Long> where keys are
     * project statuses and values are counts
     */
    public Map<String, Long> countProjectsByStatus(List<Project> projects) {
        return projects.stream()
                .collect(Collectors.groupingBy(Project::getStatus, Collectors.counting()));
    }


    /**
     * Calculate average budget by project status.
     * Groups Projects by status and calculate the average budget per status.
     */
    public Map<String,Double> averageBudgetByStatus(List<Project> projects){
        return projects.stream()
                .collect(
                        Collectors.groupingBy(
                                Project::getStatus, Collectors.averagingDouble(
                                        Project::getBudget)
                        )
                );
    }


}
