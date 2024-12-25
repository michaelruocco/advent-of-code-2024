package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Day17Test {

    @Test
    void part1Test() {
        Output output = new Output();
        Program program = new ProgramLoader(output).load("day-17/puzzle.txt");

        program.execute();

        assertThat(output.asString()).isEqualTo("7,0,7,3,4,1,3,0,1");
    }
}
