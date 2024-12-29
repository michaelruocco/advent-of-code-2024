package uk.co.mruoc.day19;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TowelPatterns {

    private final Collection<String> towels;

    public TowelPatterns(String... towels) {
        this(List.of(towels));
    }

    public long countPossible(Collection<String> designs) {
        return designs.stream().filter(this::isPossible).count();
    }

    public boolean isPossible(String design) {
        return countPossibleArrangements(design) > 0;
    }

    public long countPossibleArrangements(Collection<String> designs) {
        return designs.stream().mapToLong(this::countPossibleArrangements).sum();
    }

    public long countPossibleArrangements(String design) {
        LinkedList<Long> possibles =
                design.chars().mapToLong(c -> 0L).boxed().collect(Collectors.toCollection(LinkedList::new));
        possibles.add(0, 1L);
        int offset = 0;
        while (offset < design.length()) {
            long possible = possibles.get(offset);
            if (possible > 0) {
                for (String towel : towels) {
                    if (design.startsWith(towel, offset)) {
                        int next = offset + towel.length();
                        long existing = possibles.get(next);
                        possibles.set(next, existing + possible);
                    }
                }
            }
            offset++;
        }
        return possibles.getLast();
    }
}
