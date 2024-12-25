package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Day17Test {

    @Test
    void shouldExecuteExample1Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {2, 6};
        Registers registers = Registers.builder().c(9).build();
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
        Registers registers = Registers.builder().a(10).build();
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).containsExactly(0, 1, 2);
        assertThat(registers.getA()).isEqualTo(10);
        assertThat(registers.getB()).isZero();
        assertThat(registers.getC()).isZero();
    }

    @Test
    void shouldExecuteExample4Correctly() {
        ProgramFactory factory = new ProgramFactory();
        Output output = new Output();
        int[] input = new int[] {1, 7};
        Registers registers = Registers.builder().b(29).build();
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
        Registers registers = Registers.builder().b(2024).c(43690).build();
        Program program = factory.build(input, registers, output);

        program.execute();

        assertThat(output).isEmpty();
        assertThat(registers.getA()).isZero();
        assertThat(registers.getB()).isEqualTo(44354);
        assertThat(registers.getC()).isEqualTo(43690);
    }
}
