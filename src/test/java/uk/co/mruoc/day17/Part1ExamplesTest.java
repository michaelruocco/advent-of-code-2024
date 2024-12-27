package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Part1ExamplesTest {

    private final Program program = new Program();

    @Test
    void shouldExecuteExample1Correctly() {
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(2, 6))
                .registry(Registry.builder().c(9).build())
                .output(new Output())
                .build();

        ProgramState result = program.execute(state);

        assertThat(result.outputAsString()).isEmpty();
        assertThat(result.getA()).isZero();
        assertThat(result.getB()).isEqualTo(1);
        assertThat(result.getC()).isEqualTo(9);
    }

    @Test
    void shouldExecuteExample2Correctly() {
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(5, 0, 5, 1, 5, 4))
                .registry(Registry.builder().a(10).build())
                .output(new Output())
                .build();

        ProgramState result = program.execute(state);

        assertThat(result.outputAsString()).isEqualTo("0,1,2");
        assertThat(result.getA()).isEqualTo(10);
        assertThat(result.getB()).isZero();
        assertThat(result.getC()).isZero();
    }

    @Test
    void shouldExecuteExample3Correctly() {
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(0, 1, 5, 4, 3, 0))
                .registry(Registry.builder().a(2024).build())
                .output(new Output())
                .build();

        ProgramState result = program.execute(state);

        assertThat(result.outputAsString()).isEqualTo("4,2,5,6,7,7,7,7,3,1,0");
        assertThat(result.getA()).isZero();
        assertThat(result.getB()).isZero();
        assertThat(result.getC()).isZero();
    }

    @Test
    void shouldExecuteExample4Correctly() {
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(1, 7))
                .registry(Registry.builder().b(29).build())
                .output(new Output())
                .build();

        ProgramState result = program.execute(state);

        assertThat(result.outputAsString()).isEmpty();
        assertThat(result.getA()).isZero();
        assertThat(result.getB()).isEqualTo(26);
        assertThat(result.getC()).isZero();
    }

    @Test
    void shouldExecuteExample5Correctly() {
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(4, 0))
                .registry(Registry.builder().b(2024).c(43690).build())
                .output(new Output())
                .build();

        ProgramState result = program.execute(state);

        assertThat(result.outputAsString()).isEmpty();
        assertThat(result.getA()).isZero();
        assertThat(result.getB()).isEqualTo(44354);
        assertThat(result.getC()).isEqualTo(43690);
    }

    @Test
    void shouldExecuteExample6Correctly() {
        ProgramState state = ProgramState.builder()
                .pointer(new Pointer(0, 1, 5, 4, 3, 0))
                .registry(Registry.builder().a(729).build())
                .output(new Output())
                .build();

        ProgramState result = program.execute(state);

        assertThat(result.outputAsString()).isEqualTo("4,6,3,5,6,3,5,2,1,0");
        assertThat(result.getA()).isZero();
        assertThat(result.getB()).isZero();
        assertThat(result.getC()).isZero();
    }
}
