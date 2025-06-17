package com.company.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


public class ActiveProjectSummaryCollector implements Collector<Employee, Map<String, ActiveProjectSummary>, Map<String, ActiveProjectSummary>> {

    @Override
    public Supplier<Map<String, ActiveProjectSummary>> supplier() {
        // Creates a new empty HashMap to accumulate results
        return HashMap::new;
    }

    /**
     * a Map<String, Summary> where:
     * key = department name (e.g., "HR", "IT")
     * value = Summary of all projects assigned to employees in that department
     */
    @Override
    public BiConsumer<Map<String, ActiveProjectSummary>, Employee> accumulator() {
        return (map, emp) -> {
            ActiveProjectSummary summary =
                    map.computeIfAbsent(emp.getDepartment(), d -> new ActiveProjectSummary());
            emp.getProjects().forEach(summary::add);
        };
    }


    @Override
    public BinaryOperator<Map<String, ActiveProjectSummary>> combiner() {
        return (m1, m2) -> {
            m2.forEach((dept, summary) ->
                    m1.merge(dept, summary, ActiveProjectSummary::combine));
            return m1;
        };
    }

    @Override
    public Function<Map<String, ActiveProjectSummary>, Map<String, ActiveProjectSummary>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }

}
