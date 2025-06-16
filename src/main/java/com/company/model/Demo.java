package com.company.model;

import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Anna", "Engineering", List.of(
                        new Project("Build API", 20000, "active"),
                        new Project("Refactor Module", 10000, "completed"))),
                new Employee("Ben", "Marketing", List.of(
                        new Project("Ad Campaign", 15000, "active"))),
                new Employee("Clara", "Engineering", List.of(
                        new Project("Mobile App", 30000, "completed")))
        );

        CompanyAnalyzer analyzer = new CompanyAnalyzer();

        System.out.println("All Projects:");
        analyzer.getAllProjeccts(employees).forEach(p -> System.out.println(p.getName()));

        System.out.println("\nCount by Status:");
        System.out.println(analyzer.countProjectsByStatus(analyzer.getAllProjeccts(employees)));

        System.out.println("\nAverage Budget by Status:");
        System.out.println(analyzer.averageBudgetByStatus(analyzer.getAllProjeccts(employees)));

        System.out.println("\nSummary by Department (Custom Collector):");
        Map<String, ProjectSummaryCollector.Summary> summary = employees.stream()
                .collect(new ProjectSummaryCollector());
        summary.forEach((dept, s) -> System.out.println(dept + ": " + s));

    }
}
