package uk.co.mruoc.day6;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.Point;

class StepTest {

    @Test
    void shouldReturnTrueIfHaveSameValues() {
        Step step1 = new Step(new Point(87, 110), Direction.NORTH);
        Step step2 = new Step(new Point(87, 110), Direction.NORTH);

        boolean equal = step1.equals(step2);

        assertThat(equal).isTrue();
    }
}
