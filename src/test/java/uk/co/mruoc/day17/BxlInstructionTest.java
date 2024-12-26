package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BxlInstructionTest {

    private final Instruction bxl = new BxlInstruction();

    @Test
    void shouldCalculateCorrectValue() {
        Registry registry = new Registry();
        registry.setB(29);
        ProgramState state = ProgramState.builder().registry(registry).build();

        bxl.execute(7, state);

        assertThat(registry.getA()).isZero();
        assertThat(registry.getB()).isEqualTo(26);
        assertThat(registry.getC()).isZero();
    }
}
