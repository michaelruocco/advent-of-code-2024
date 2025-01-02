package uk.co.mruoc.day23;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComputerFinder {

    public String findLanPartyPassword(Map<String, Collection<String>> computers) {
        return String.join(",", findLargestGroup(computers));
    }

    public Collection<Collection<String>> findUniqueTriplets(Map<String, Collection<String>> computers) {
        return findUniqueTriplets(computers, s -> s.charAt(0) == 't');
    }

    public Collection<Collection<String>> findUniqueTriplets(
            Map<String, Collection<String>> computers, Predicate<String> predicate) {
        return computers.entrySet().stream()
                .filter(e -> predicate.test(e.getKey()))
                .map(e -> processEntry(computers, e))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private static Collection<Collection<String>> processEntry(
            Map<String, Collection<String>> computers, Map.Entry<String, Collection<String>> entry) {
        Collection<Collection<String>> triplets = new HashSet<>();
        for (String c2 : entry.getValue()) {
            for (String c3 : computers.getOrDefault(c2, Set.of())) {
                String c1 = entry.getKey();
                if (!c1.equals(c3) && computers.getOrDefault(c3, Set.of()).contains(c1)) {
                    triplets.add(Stream.of(c1, c2, c3).sorted().toList());
                }
            }
        }
        return triplets;
    }

    public Collection<String> findLargestGroup(Map<String, Collection<String>> computers) {
        return computers.keySet().stream()
                .map(key -> findLargestGroup(computers, List.of(key)))
                .max(Comparator.comparingInt(Collection::size))
                .orElseThrow();
    }

    private static Collection<String> findLargestGroup(
            Map<String, Collection<String>> computers, List<String> currentGroup) {
        return currentGroup.stream()
                .map(computers::get)
                .reduce(ComputerFinder::intersection)
                .orElse(Collections.emptySet())
                .stream()
                .filter(connection -> connection.compareTo(currentGroup.get(currentGroup.size() - 1)) > 0)
                .map(nextGroup -> findLargestGroup(computers, currentGroup, nextGroup))
                .max(Comparator.comparingInt(Collection::size))
                .orElse(currentGroup);
    }

    private static Collection<String> findLargestGroup(
            Map<String, Collection<String>> computers, Collection<String> currentGroup, String nextGroup) {
        List<String> group =
                Stream.concat(currentGroup.stream(), Stream.of(nextGroup)).toList();
        return findLargestGroup(computers, group);
    }

    private static Collection<String> intersection(Collection<String> c1, Collection<String> c2) {
        Collection<String> intersection = new HashSet<>(c1);
        intersection.retainAll(c2);
        return intersection;
    }
}
