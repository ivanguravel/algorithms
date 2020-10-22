package org.ivzh.graph;

import java.util.*;

// https://leetcode.com/problems/employee-importance/
class EmployeeImportance {
    public int getImportance(List<Employee> employees, int id) {
        Employee e = null;
        Map<Integer, Employee> graph = new HashMap<>();
        for (Employee element : employees) {
            if (element.id == id) {
                e = element;
            }
            graph.put(element.id, element);
        }
        return bfsSum(e, graph);
    }

    
    private int bfsSum(Employee e, Map<Integer, Employee> graph) {
        int answer = e.importance;
        Deque<Employee> deq = new ArrayDeque<>();
        deq.addAll(findEmployees(graph, e.subordinates));
        while (!deq.isEmpty()) {
            e = deq.pollFirst();
            answer = answer + e.importance;
            deq.addAll(findEmployees(graph, e.subordinates));
        }
        return answer;
    }
             
    private List<Employee> findEmployees(Map<Integer, Employee> graph, List<Integer> subordinates) {
        List<Employee> result = new LinkedList<>();
        Employee e;
        for (Integer i : subordinates) {
            e = graph.get(i);
            if (e!= null) {
                result.add(e);
            }
        }
        return result;
    }
}
