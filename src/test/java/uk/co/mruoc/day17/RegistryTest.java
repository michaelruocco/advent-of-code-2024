package uk.co.mruoc.day17;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class RegistryTest {

    @Test
    void shouldThrowExceptionForComboOperandWithInvalidLiteralOperand() {
        Registry registry = new Registry();
        int invalidLiteralOperand = 7;

        Throwable error = catchThrowable(() -> registry.toComboOperand(invalidLiteralOperand));

        assertThat(error)
                .isInstanceOf(InvalidComboOperandException.class)
                .hasMessage(Integer.toString(invalidLiteralOperand));
    }
}
