package uk.co.mruoc.day9;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.mruoc.file.FileLoader;

class Day9Test {

    @ParameterizedTest
    @CsvSource({
        "111111111111111111111,0.1.2.3.4.5.6.7.8.9.10",
        "121111111111111111111,0..1.2.3.4.5.6.7.8.9.10",
        "131111111111111111111,0...1.2.3.4.5.6.7.8.9.10",
        "1513131313111111111111111,0.....1...2...3...4...5.6.7.8.9.10.11.12",
        "2333133121414131402,00...111...2...333.44.5555.6666.777.888899",
        "90909,000000000111111111222222222"
    })
    void shouldExpandDenseInput(String input, String expectedState) {
        DiskMap map = new DiskMap(input);

        String state = map.getState();

        assertThat(state).isEqualTo(expectedState);
    }

    @ParameterizedTest
    @CsvSource({
        "111111111111111111111,010192837465..........",
        "121111111111111111111,010918273645...........",
        "131111111111111111111,010981726354............",
        "1513131313111111111111111,0121110981765243........................",
        "2333133121414131402,0099811188827773336446555566..............",
        "90909,000000000111111111222222222"
    })
    void shouldCompactCorrectlyForPart1(String input, String expectedState) {
        DiskMap map = new DiskMap(input);
        System.out.println(map.getState());
        Compactor compactor = new Part1Compactor();

        DiskMap compacted = compactor.compact(map);

        assertThat(compacted.getState()).isEqualTo(expectedState);
    }

    @ParameterizedTest
    @CsvSource({
        "111111111111111111111,010192837465..........",
        "121111111111111111111,010918273645...........",
        "131111111111111111111,010981726354............",
        "1513131313111111111111111,0121110981765243........................",
        "2333133121414131402,00992111777.44.333....5555.6666.....8888..",
        "90909,000000000111111111222222222"
    })
    void shouldCompactCorrectlyForPart2(String input, String expectedState) {
        DiskMap map = new DiskMap(input);
        System.out.println(map.getState());
        Compactor compactor = new Part2Compactor();

        DiskMap compacted = compactor.compact(map);

        assertThat(compacted.getState()).isEqualTo(expectedState);
    }

    @ParameterizedTest
    @CsvSource({
        "111111111111111111111,290",
        "121111111111111111111,270",
        "131111111111111111111,254",
        "1513131313111111111111111,388",
        "2333133121414131402,1928",
        "90909,513"
    })
    void shouldCalculateChecksumCorrectly(String input, String expectedChecksum) {
        DiskMap map = new DiskMap(input);
        Compactor compactor = new Part1Compactor();

        long checksum = compactor.compact(map).checksum();

        assertThat(checksum).isEqualTo(Long.parseLong(expectedChecksum));
    }

    @ParameterizedTest
    @MethodSource("compactorAndExpectedChecksum")
    void shouldCalculatePuzzleChecksum(Compactor compactor, long expectedChecksum) {
        String input = FileLoader.loadContentFromClasspath("day-9/disk-map.txt");
        DiskMap map = new DiskMap(input);

        DiskMap compacted = compactor.compact(map);

        assertThat(compacted.checksum()).isEqualTo(expectedChecksum);
    }

    private static Stream<Arguments> compactorAndExpectedChecksum() {
        return Stream.of(
                Arguments.of(new Part1Compactor(), 6398608069280L), Arguments.of(new Part2Compactor(), 6427437134372L));
    }
}
