package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BdvInstructionTest {

    private final Instruction bdv = new BdvInstruction();

    @Test
    void shouldStoreResultInRegistryB() {
        ProgramState initial = ProgramState.builder()
                .registry(Registry.builder().a(100).build())
                .build();

        ProgramState result = bdv.execute(3, initial);

        assertThat(result.getA()).isEqualTo(100);
        assertThat(result.getB()).isEqualTo(12);
        assertThat(result.getC()).isZero();
    }
}
