package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BxlInstructionTest {

    @Test
    void shouldCalculateCorrectValue() {
        Registers registers = new Registers();
        registers.setB(29);
        Instruction bxl = new BxlInstruction(registers);

        bxl.accept(7);

        assertThat(registers.getA()).isZero();
        assertThat(registers.getB()).isEqualTo(26);
        assertThat(registers.getC()).isZero();
    }
}
