import java.util.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Step 1: Build the Graph
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            
            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(u).put(v, val);
            graph.get(v).put(u, 1.0 / val);
        }

        // Step 2: Evaluate Queries
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                results[i] = -1.0;
            } else {
                results[i] = dfs(start, end, 1.0, graph, new HashSet<>());
            }
        }
        return results;
    }

    private double dfs(String curr, String target, double product, 
                       Map<String, Map<String, Double>> graph, Set<String> visited) {
        if (curr.equals(target)) return product;
        visited.add(curr);
        
        Map<String, Double> neighbors = graph.get(curr);
        for (String next : neighbors.keySet()) {
            if (!visited.contains(next)) {
                double result = dfs(next, target, product * neighbors.get(next), graph, visited);
                if (result != -1.0) return result;
            }
        }
        return -1.0;
    }
}