package uk.co.mruoc.day5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import org.junit.jupiter.api.Test;

class PageTest {

    @Test
    void shouldReturnBeforeValues() {
        Page page = new Page(75, 47, 61, 53, 29);

        Collection<Integer> before = page.getPagesBefore(61);

        assertThat(before).containsExactly(75, 47);
    }

    @Test
    void shouldReturnAfterValues() {
        Page page = new Page(75, 47, 61, 53, 29);

        Collection<Integer> before = page.getPagesAfter(61);

        assertThat(before).containsExactly(53, 29);
    }

    @Test
    void shouldReturnMiddleOfOddNumbers() {
        Page page = new Page(75, 47, 61, 53, 29);

        int middle = page.getMiddleNumber();

        assertThat(middle).isEqualTo(61);
    }

    @Test
    void shouldReturnMiddleOfEvenNumbers() {
        Page page = new Page(75, 47, 53, 29);

        int middle = page.getMiddleNumber();

        assertThat(middle).isEqualTo(53);
    }
}
