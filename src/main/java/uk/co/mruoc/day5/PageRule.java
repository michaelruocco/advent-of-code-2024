package uk.co.mruoc.day5;

import java.util.Collection;
import java.util.HashSet;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PageRule {

    private final Integer number;
    private final Collection<Integer> before;
    private final Collection<Integer> after;

    public PageRule(Integer number) {
        this(number, new HashSet<>(), new HashSet<>());
    }

    public void addBefore(Integer i) {
        before.add(i);
    }

    public void addAfter(Integer i) {
        after.add(i);
    }

    public boolean isBefore(int other) {
        return before.contains(other);
    }

    public boolean isAfter(int other) {
        return after.contains(other);
    }

    public boolean isCorrectlyOrdered(Page page, int number) {
        return before.containsAll(page.getPagesBefore(number));
    }
}
