package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Part2ExamplesTest {

    @Test
    void shouldCalculateExample1Correctly() {
        ProgramState initial = ProgramState.builder()
                .pointer(new Pointer(0, 3, 5, 4, 3, 0))
                .registry(new Registry())
                .output(new Output())
                .build();
        ProgramFinder finder = new ProgramFinder(new Program());

        long result = finder.find(initial);

        assertThat(result).isEqualTo(117440);
    }
}
