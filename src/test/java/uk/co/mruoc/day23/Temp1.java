package uk.co.mruoc.day23;

import java.util.*;

public class Temp1 {
    public static List<String> getLongestGroup(Map<String, List<String>> connections, List<String> currentGroup) {
        // Create a set of common connections that are greater than the last element of the current group
        Set<String> commonConnections = new HashSet<>(connections.get(currentGroup.get(currentGroup.size() - 1)));

        // Iterate over each element in the current group and intersect the connections
        for (String element : currentGroup) {
            Set<String> elementConnections = new HashSet<>(connections.get(element));
            commonConnections.retainAll(elementConnections);
        }

        // Filter out those that are greater than the last element of the current group
        List<String> filteredCommonConnections = new ArrayList<>();
        for (String conn : commonConnections) {
            if (conn.compareTo(currentGroup.get(currentGroup.size() - 1)) > 0) {
                filteredCommonConnections.add(conn);
            }
        }

        // If no common connections are found, return the current group
        if (filteredCommonConnections.isEmpty()) {
            return currentGroup;
        }

        // Recursively find the longest group by exploring each of the common connections
        List<String> longestGroup = null;
        for (String next : filteredCommonConnections) {
            List<String> nextGroup = new ArrayList<>(currentGroup);
            nextGroup.add(next);
            List<String> group = getLongestGroup(connections, nextGroup);

            if (longestGroup == null || group.size() > longestGroup.size()) {
                longestGroup = group;
            }
        }

        return longestGroup;
    }

    public static void main(String[] args) {
        // Example usage:
        Map<String, List<String>> connections = new HashMap<>();
        connections.put("A", Arrays.asList("B", "C"));
        connections.put("B", Arrays.asList("A", "C", "D"));
        connections.put("C", Arrays.asList("A", "B", "D"));
        connections.put("D", Arrays.asList("B", "C"));

        List<String> currentGroup = Arrays.asList("A");

        List<String> longestGroup = getLongestGroup(connections, currentGroup);
        System.out.println(longestGroup); // Should print the longest group
    }
}
