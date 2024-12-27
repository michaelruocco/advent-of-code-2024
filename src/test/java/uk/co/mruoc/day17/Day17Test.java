package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Day17Test {

    private final ProgramState initial = new ProgramStateLoader().load("day-17/puzzle.txt");

    private final Program program = new Program();

    @Test
    void part1Test() {
        ProgramState result = program.execute(initial);

        assertThat(result.outputAsString()).isEqualTo("7,0,7,3,4,1,3,0,1");
    }

    @Test
    void part2Test() {
        ProgramFinder finder = new ProgramFinder(program);

        long result = finder.find(initial);

        assertThat(result).isEqualTo(156985331222018L);
    }
}
