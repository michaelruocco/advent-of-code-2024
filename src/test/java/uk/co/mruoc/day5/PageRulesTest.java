package uk.co.mruoc.day5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PageRulesTest {

    @Test
    void shouldAddRuleCorrectlyForBeforeValue() {
        PageRules rules = new PageRules("75|47");

        PageRule rule = rules.get(75);

        assertThat(rule.getNumber()).isEqualTo(75);
        assertThat(rule.getBefore()).isEmpty();
        assertThat(rule.getAfter()).containsExactly(47);
    }

    @Test
    void shouldAddRuleCorrectlyForAfterValue() {
        PageRules rules = new PageRules("75|47");

        PageRule rule = rules.get(47);

        assertThat(rule.getNumber()).isEqualTo(47);
        assertThat(rule.getBefore()).containsExactly(75);
        assertThat(rule.getAfter()).isEmpty();
    }

    @Test
    void shouldAddMultipleRulesForSameNumberCorrectly() {
        PageRules rules = new PageRules("75|47", "47|61");

        PageRule rule = rules.get(47);

        assertThat(rule.getNumber()).isEqualTo(47);
        assertThat(rule.getBefore()).containsExactly(75);
        assertThat(rule.getAfter()).containsExactly(61);
    }

    @Test
    void shouldValidatePageCorrectly() {
        PageRules rules =
                new PageRules("75|47", "75|61", "75|53", "75|29", "47|61", "47|53", "47|29", "61|53", "61|29", "53|29");
        Page page = new Page(75, 47, 61, 53, 29);

        boolean correct = rules.isCorrectlyOrdered(page);

        assertThat(correct).isTrue();
    }
}
