package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ExamplesTest {

    private final Registers registers = new Registers();

    @Test
    void shouldExecuteExample1Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {2, 6};
        registers.setC(9);
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).isEmpty();
        assertThat(registers.getA()).isZero();
        assertThat(registers.getB()).isEqualTo(1);
        assertThat(registers.getC()).isEqualTo(9);
    }

    @Test
    void shouldExecuteExample2Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {5, 0, 5, 1, 5, 4};
        registers.setA(10);
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).containsExactly(0, 1, 2);
        assertThat(registers.getA()).isEqualTo(10);
        assertThat(registers.getB()).isZero();
        assertThat(registers.getC()).isZero();
    }

    @Test
    void shouldExecuteExample3Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {0, 1, 5, 4, 3, 0};
        registers.setA(2024);
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).containsExactly(4, 2, 5, 6, 7, 7, 7, 7, 3, 1, 0);
        assertThat(registers.getA()).isZero();
        assertThat(registers.getB()).isZero();
        assertThat(registers.getC()).isZero();
    }

    @Test
    void shouldExecuteExample4Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {1, 7};
        registers.setB(29);
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).isEmpty();
        assertThat(registers.getA()).isZero();
        assertThat(registers.getB()).isEqualTo(26);
        assertThat(registers.getC()).isZero();
    }

    @Test
    void shouldExecuteExample5Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {4, 0};
        registers.setB(2024);
        registers.setC(43690);
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).isEmpty();
        assertThat(registers.getA()).isZero();
        assertThat(registers.getB()).isEqualTo(44354);
        assertThat(registers.getC()).isEqualTo(43690);
    }

    @Test
    void shouldExecuteExample6Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {0, 1, 5, 4, 3, 0};
        registers.setA(729);
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).containsExactly(4, 6, 3, 5, 6, 3, 5, 2, 1, 0);
        assertThat(registers.getA()).isZero();
        assertThat(registers.getB()).isZero();
        assertThat(registers.getC()).isZero();
    }
}
