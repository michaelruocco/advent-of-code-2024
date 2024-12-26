package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ExamplesTest {

    private final Program program = new Program();
    private final Registry registry = new Registry();

    @Test
    void shouldExecuteExample1Correctly() {
        Output output = new Output();
        registry.setC(9);
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(2, 6))
                .registry(registry)
                .output(output)
                .build();

        program.execute(state);

        assertThat(output).isEmpty();
        assertThat(registry.getA()).isZero();
        assertThat(registry.getB()).isEqualTo(1);
        assertThat(registry.getC()).isEqualTo(9);
    }

    @Test
    void shouldExecuteExample2Correctly() {
        Output output = new Output();
        registry.setA(10);
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(5, 0, 5, 1, 5, 4))
                .registry(registry)
                .output(output)
                .build();

        program.execute(state);

        assertThat(output).containsExactly(0, 1, 2);
        assertThat(registry.getA()).isEqualTo(10);
        assertThat(registry.getB()).isZero();
        assertThat(registry.getC()).isZero();
    }

    @Test
    void shouldExecuteExample3Correctly() {
        Output output = new Output();
        registry.setA(2024);
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(0, 1, 5, 4, 3, 0))
                .registry(registry)
                .output(output)
                .build();

        program.execute(state);

        assertThat(output).containsExactly(4, 2, 5, 6, 7, 7, 7, 7, 3, 1, 0);
        assertThat(registry.getA()).isZero();
        assertThat(registry.getB()).isZero();
        assertThat(registry.getC()).isZero();
    }

    @Test
    void shouldExecuteExample4Correctly() {
        Output output = new Output();
        registry.setB(29);
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(1, 7))
                .registry(registry)
                .output(output)
                .build();

        program.execute(state);

        assertThat(output).isEmpty();
        assertThat(registry.getA()).isZero();
        assertThat(registry.getB()).isEqualTo(26);
        assertThat(registry.getC()).isZero();
    }

    @Test
    void shouldExecuteExample5Correctly() {
        Output output = new Output();
        registry.setB(2024);
        registry.setC(43690);
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(4, 0))
                .registry(registry)
                .output(output)
                .build();

        program.execute(state);

        assertThat(output).isEmpty();
        assertThat(registry.getA()).isZero();
        assertThat(registry.getB()).isEqualTo(44354);
        assertThat(registry.getC()).isEqualTo(43690);
    }

    @Test
    void shouldExecuteExample6Correctly() {
        Output output = new Output();
        registry.setA(729);
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(0, 1, 5, 4, 3, 0))
                .registry(registry)
                .output(output)
                .build();

        program.execute(state);

        assertThat(output).containsExactly(4, 6, 3, 5, 6, 3, 5, 2, 1, 0);
        assertThat(registry.getA()).isZero();
        assertThat(registry.getB()).isZero();
        assertThat(registry.getC()).isZero();
    }
}
