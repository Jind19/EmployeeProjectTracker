package com.company.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Anna", "Engineering", List.of(
                        new Project("Build API", 20000, "active", "Max", "internal", LocalDate.of(2025, 8, 15)),
                        new Project("Refactor Module", 10000, "completed", "Max", "internal", LocalDate.of(2025, 4, 10)))),
                new Employee("Ben", "Marketing", List.of(
                        new Project("Ad Campaign", 15000, "active", "Lisa", "external", LocalDate.of(2025, 9, 1)))),
                new Employee("Clara", "Engineering", List.of(
                        new Project("Mobile App", 30000, "completed", "Max", "external", LocalDate.of(2025, 5, 30))))
        );


        CompanyAnalyzer analyzer = new CompanyAnalyzer();

        System.out.println("All Projects:");
        analyzer.getAllProjects(employees).forEach(p -> System.out.println(p.getName()));

        System.out.println("\nCount by Status:");
        System.out.println(analyzer.countProjectsByStatus(analyzer.getAllProjects(employees)));

        System.out.println("\nAverage Budget by Status:");
        System.out.println(analyzer.averageBudgetByStatus(analyzer.getAllProjects(employees)));

        System.out.println("\n🔎 Active Project Summary by Department:");
        Map<String, ActiveProjectSummary> activeSummary = employees.stream()
                .collect(new ActiveProjectSummaryCollector());

        activeSummary.forEach((dept, summary) ->
                System.out.println(dept + " ➤ " + summary));

        // List of Projects Due Before a Certain Date
        List<String> urgentProjects = employees.stream()
                .flatMap(e -> e.getProjects().stream())
                .filter(p -> p.getDeadline().isBefore(LocalDate.of(2025, 6, 1)))
                .map(Project::getName)
                .collect(Collectors.toList());

        System.out.println("\nUrgent Projects (Due before June 1, 2025):");
        urgentProjects.forEach(System.out::println);





    }
}
