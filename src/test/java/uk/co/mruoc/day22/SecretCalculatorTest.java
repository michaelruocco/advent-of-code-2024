package uk.co.mruoc.day22;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import org.junit.jupiter.api.Test;

class SecretCalculatorTest {

    private final SecretCalculator calculator = new SecretCalculator();

    @Test
    void shouldCalculateNextNSecretNumbers() {
        long input = 123;

        Collection<Long> secrets = calculator.calculateNextN(input, 10);

        assertThat(secrets)
                .containsExactly(
                        15887950L, 16495136L, 527345L, 704524L, 1553684L, 12683156L, 11100544L, 12249484L, 7753432L,
                        5908254L);
    }
}
