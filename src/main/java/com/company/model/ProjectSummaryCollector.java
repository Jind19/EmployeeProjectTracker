package com.company.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * A custom collector that groups projects by status and calculates
 *  * the total count and average budget for each status.
 */

public class ProjectSummaryCollector implements Collector<Employee, Map<String, ProjectSummaryCollector.Summary>, Map<String, ProjectSummaryCollector.Summary>> {

    public static class Summary {
        private int totalProjects = 0;
        private double totalBudget = 0.0;

        public void addProject(Project p) {
            totalProjects++;
            totalBudget += p.getBudget();
        }

        public int getTotalProjects() {
            return totalProjects;
        }

        public double getAverageBudget() {
            return totalProjects == 0 ? 0 : totalBudget / totalProjects;
        }

        @Override
        public String toString() {
            return "Projects: " + totalProjects + ", Avg Budget: " + getAverageBudget();
        }

    }

    @Override
    public Supplier<Map<String, Summary>> supplier() {
        // Creates a new empty HashMap to accumulate results
        return HashMap::new;
    }

    /**
     * a Map<String, Summary> where:
     * key = department name (e.g., "HR", "IT")
     * value = Summary of all projects assigned to employees in that department
     */
    @Override
    public BiConsumer<Map<String, Summary>, Employee> accumulator() {
        // For the employees department, update (or create if not exists) the project's summary

        return (map, employee) -> {
            Summary summary = map.computeIfAbsent(employee.getDepartment(), d -> new Summary());
            //Adds all the employee’s projects to the summary.
            employee.getProjects().forEach(summary::addProject);
        };
    }

    @Override
    public BinaryOperator<Map<String, Summary>> combiner() {
        return (map1, map2) -> {
            map2.forEach((dept, s2) ->
                    map1.merge(dept, s2, (s1, s) -> {
                        s1.totalProjects += s.totalProjects;
                        s1.totalBudget += s.totalBudget;
                        return s1;
                    }));
            return map1;
        };
    }

    @Override
    public Function<Map<String, Summary>, Map<String, Summary>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }

}
