package uk.co.mruoc.day6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StepTest {

    @Test
    void shouldReturnTrueIfHaveSameValues() {
        Step step1 = new Step(new LabMap.Point(87, 110), '^');
        Step step2 = new Step(new LabMap.Point(87, 110), '^');

        boolean equal = step1.equals(step2);

        assertThat(equal).isTrue();
    }

    @Test
    void shouldReturnTrueListContainsStepWithSameValues() {
        Step step1 = new Step(new LabMap.Point(87, 110), '^');
        Step step2 = new Step(new LabMap.Point(87, 110), '^');

        boolean contains = List.of(step1).contains(step2);

        assertThat(contains).isTrue();
    }
}
