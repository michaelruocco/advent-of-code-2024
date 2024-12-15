package uk.co.mruoc.day13;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ClawMachineTest {

    @Nested
    class Example1Test {

        private final Prize prize = new Prize(8400, 5400);

        private final ClawMachine.ClawMachineBuilder machineBuilder =
                ClawMachine.builder().a(new Button("A", 94, 34)).b(new Button("B", 22, 67));

        @Test
        void part1() {
            ClawMachine machine = machineBuilder.prize(prize).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).contains(280L);
        }

        @Test
        void part2() {
            ClawMachine machine = machineBuilder.prize(prize.toPart2()).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).isEmpty();
        }
    }

    @Nested
    class Example2Test {

        private final Prize prize = new Prize(12748, 12176);

        private final ClawMachine.ClawMachineBuilder machineBuilder =
                ClawMachine.builder().a(new Button("A", 26, 66)).b(new Button("B", 67, 21));

        @Test
        void part1() {
            ClawMachine machine = machineBuilder.prize(prize).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).isEmpty();
        }

        @Test
        void part2() {
            ClawMachine machine = machineBuilder.prize(prize.toPart2()).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).contains(459236326669L);
        }
    }

    @Nested
    class Example3Test {

        private final Prize prize = new Prize(7870, 6450);

        private final ClawMachine.ClawMachineBuilder machineBuilder =
                ClawMachine.builder().a(new Button("A", 17, 86)).b(new Button("B", 84, 37));

        @Test
        void part1() {
            ClawMachine machine = machineBuilder.prize(prize).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).contains(200L);
        }

        @Test
        void part2() {
            ClawMachine machine = machineBuilder.prize(prize.toPart2()).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).isEmpty();
        }
    }

    @Nested
    class Example4Test {

        private final Prize prize = new Prize(18641, 10279);

        private final ClawMachine.ClawMachineBuilder machineBuilder =
                ClawMachine.builder().a(new Button("A", 69, 23)).b(new Button("B", 27, 71));

        @Test
        void part1() {
            ClawMachine machine = machineBuilder.prize(prize).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).isEmpty();
        }

        @Test
        void part2() {
            ClawMachine machine = machineBuilder.prize(prize.toPart2()).build();

            Optional<Long> tokens = machine.calculateCost();

            assertThat(tokens).contains(416082282239L);
        }
    }
}
