package uk.co.mruoc.day6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class GridTest {
    @Test
    void shouldThrowExceptionIfGuardNotFoundInGrid() {
        Grid grid = new Grid(new char[2][2]);

        Throwable error = catchThrowable(grid::findGuard);

        assertThat(error).isInstanceOf(IllegalStateException.class).hasMessage("guard not found in grid");
    }
}
