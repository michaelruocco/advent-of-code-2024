package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BxlInstructionTest {

    private final Instruction bxl = new BxlInstruction();

    @Test
    void shouldCalculateCorrectValue() {
        ProgramState initial = ProgramState.builder()
                .registry(Registry.builder().b(29).build())
                .build();

        ProgramState result = bxl.execute(7, initial);

        assertThat(result.getA()).isZero();
        assertThat(result.getB()).isEqualTo(26);
        assertThat(result.getC()).isZero();
    }
}
