package uk.co.mruoc.day23;

import java.io.*;
import java.util.*;

public class Temp {

    public static void main(String[] args) throws IOException {
        ComputerLoader loader = new ComputerLoader();
        Map<String, Collection<String>> map = loader.load("day-23/puzzle.txt");

        ComputerFinder finder = new ComputerFinder();
        Collection<Collection<String>> current = finder.findUniqueTriplets(map);
        while (true) {
            Collection<Collection<String>> next = new HashSet<>();
            for (Collection<String> s : current) {
                for (Map.Entry<String, Collection<String>> entry : map.entrySet()) {
                    String ele = entry.getKey();
                    Collection<String> connections = entry.getValue();
                    if (connections.size() < s.size()) {
                        continue;
                    }

                    boolean containsAll = true;
                    for (String sEle : s) {
                        if (!connections.contains(sEle)) {
                            containsAll = false;
                            break;
                        }
                    }

                    if (containsAll) {
                        Set<String> superset = new HashSet<>(s);
                        superset.add(ele);
                        next.add(superset);
                    }
                }
            }
            if (next.isEmpty()) {
                break;
            }
            current = next;
        }

        // Find the final set of strings
        Collection<String> lanPartySet = current.iterator().next();

        // Convert to list, sort and print the result
        List<String> lanPartyList = new ArrayList<>(lanPartySet);
        Collections.sort(lanPartyList);

        for (int i = 0; i < lanPartyList.size() - 1; i++) {
            System.out.print(lanPartyList.get(i) + ",");
        }
        System.out.println(lanPartyList.get(lanPartyList.size() - 1));
    }
}
