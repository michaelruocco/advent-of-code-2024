package uk.co.mruoc.day5;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PageRules implements Comparator<Integer> {

    private final Map<Integer, PageRule> rules;

    public PageRules(String... inputRules) {
        this(List.of(inputRules));
    }

    public PageRules(Collection<String> inputRules) {
        this(new HashMap<>());
        inputRules.forEach(this::add);
    }

    public PageRule get(int number) {
        return rules.get(number);
    }

    public boolean isCorrectlyOrdered(Page page) {
        return page.stream().allMatch(number -> rules.get(number).isCorrectlyOrdered(page, number));
    }

    public Page correct(Page page) {
        return new Page(page.stream().sorted(this).toList());
    }

    private void add(String rule) {
        String[] parts = rule.split("\\|");
        int before = Integer.parseInt(parts[0]);
        int after = Integer.parseInt(parts[1]);
        add(before, after);
    }

    private void add(Integer before, Integer after) {
        updateBefore(before, after);
        updateAfter(before, after);
    }

    private void updateBefore(Integer before, Integer after) {
        if (rules.containsKey(before)) {
            rules.get(before).addAfter(after);
        } else {
            PageRule rule = new PageRule(before);
            rule.addAfter(after);
            rules.put(before, rule);
        }
    }

    private void updateAfter(Integer before, Integer after) {
        if (rules.containsKey(after)) {
            rules.get(after).addBefore(before);
        } else {
            PageRule rule = new PageRule(after);
            rule.addBefore(before);
            rules.put(after, rule);
        }
    }

    @Override
    public int compare(Integer i1, Integer i2) {
        PageRule rule = rules.get(i1);
        if (rule.isBefore(i2)) {
            return 1;
        }
        if (rule.isAfter(i2)) {
            return -1;
        }
        return 0;
    }
}
