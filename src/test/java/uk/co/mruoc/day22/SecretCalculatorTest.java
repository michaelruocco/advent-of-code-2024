package uk.co.mruoc.day22;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @ParameterizedTest
    @MethodSource("inputAndExpectedSecret")
    void shouldCalculateSecretNumberOfDay(long input, long expectedSecret) {
        long secret = calculator.calculateLastOfDay(input);

        assertThat(secret).isEqualTo(expectedSecret);
    }

    @Test
    void shouldCalculateSumOfSecretNumbersOfDay() {
        Collection<Long> inputs = List.of(1L, 10L, 100L, 2024L);

        long sum = calculator.calculateSumOfLastOfDay(inputs);

        assertThat(sum).isEqualTo(37327623);
    }

    @Test
    void shouldCalculateMostBananas() {
        Collection<Long> inputs = List.of(1L, 2L, 3L, 2024L);

        long most = calculator.calculateMostBananas1(inputs);

        assertThat(most).isEqualTo(23);
    }

    private static Stream<Arguments> inputAndExpectedSecret() {
        return Stream.of(
                Arguments.of(1, 8685429),
                Arguments.of(10, 4700978),
                Arguments.of(100, 15273692),
                Arguments.of(2024, 8667524));
    }
}
