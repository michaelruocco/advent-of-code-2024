package uk.co.mruoc.day6;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StepTest {

    @Test
    void shouldReturnTrueIfHaveSameValues() {
        Step step1 = new Step(new Location(87, 110), '^');
        Step step2 = new Step(new Location(87, 110), '^');

        boolean equal = step1.equals(step2);

        assertThat(equal).isTrue();
    }
}
